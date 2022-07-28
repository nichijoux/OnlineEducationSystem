package com.zh.oes.auth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.oes.auth.mapper.PermissionMapper;
import com.zh.oes.auth.service.PermissionService;
import com.zh.oes.auth.service.RolePermissionService;
import com.zh.oes.auth.service.UserService;
import com.zh.oes.model.entity.auth.Permission;
import com.zh.oes.model.entity.auth.RolePermission;
import com.zh.oes.model.entity.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    private UserService userService;

    private RolePermissionService rolePermissionService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRolePermissionService(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    /**
     * 获取所有的权限信息(并封装为树形结构)
     *
     * @return 封装好的权限信息, 为顶层权限
     */
    @Override
    public Permission getAllPermission() {
        List<Permission> permissionList = getAllPermissionList();
        return buildPermission(permissionList);
    }

    /**
     * 根据角色id获取角色的权限信息
     *
     * @param roleId 要查询的角色id
     * @return 封装好的权限
     */
    @Override
    public Permission getRolePermission(Long roleId) {
        List<Permission> permissionList = getAllPermissionList();
        // roleId对应的角色权限
        List<RolePermission> rolePermissionList = rolePermissionService.list(
                new LambdaQueryWrapper<RolePermission>().eq(RolePermission::getRoleId, roleId));
        // 这样选择的权限会导致上层权限虽然没有被完全选取,但还是设置select为了true
        for (Permission permission : permissionList) {
            for (RolePermission rolePermission : rolePermissionList) {
                if (rolePermission.getPermissionId().equals(permission.getId())) {
                    permission.setSelect(true);
                }
            }
        }
        // 建立树形结构后再设置select属性
        Permission topPermission = buildPermission(permissionList);
        setSelectPermission(topPermission);
        return topPermission;
    }

    /**
     * 给roleId的角色分配permissionIdList的权限
     *
     * @param roleId           角色id
     * @param permissionIdList 要分配的权限id集
     */
    @Override
    public void assignRolePermission(Long roleId, Long[] permissionIdList) {
        // 删除原角色的权限
        rolePermissionService.remove(new LambdaQueryWrapper<RolePermission>().eq(RolePermission::getRoleId, roleId));
        // 将角色id和权限id集合封装为RolePermission对象
        List<RolePermission> rolePermissionList = new ArrayList<>();
        for (Long permissionId : permissionIdList) {
            if (Objects.nonNull(permissionId)) {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(roleId);
                rolePermission.setPermissionId(permissionId);
                rolePermissionList.add(rolePermission);
            }
        }
        // 保存到数据库
        rolePermissionService.saveBatch(rolePermissionList);
    }

    /**
     * 递归删除权限菜单
     *
     * @param permissionId 要删除的权限菜单的id
     */
    @Override
    public boolean deletePermissionById(Long permissionId) {
        List<Permission> permissionList = getAllPermissionList();
        List<Long> childIdList = getChildIdList(permissionId, permissionList);
        childIdList.add(permissionId);
        return baseMapper.deleteBatchIds(childIdList) >= 1;
    }

    /**
     * 根据用户id获取动态菜单
     *
     * @param userId 用户id
     * @return 动态菜单列表
     */
    @Override
    public List<JSONObject> getPermissionByUserId(Long userId) {
        List<Permission> permissionList;
        if (isSystemAdmin(userId)) {
            permissionList = baseMapper.selectList(new LambdaQueryWrapper<Permission>().orderByAsc(Permission::getSort));
        } else {
            permissionList = baseMapper.selectPermissionListByUserId(userId);
        }
        // 多角色去重
        Set<Permission> multipleRolePermissionSet = new HashSet<>(permissionList);
        permissionList = new ArrayList<>(multipleRolePermissionSet);
        permissionList.sort(Comparator.comparing(Permission::getSort));
        return buildMenu(buildPermission(permissionList));
    }

    /**
     * 根据用户id获取权限值
     *
     * @param userId 用户id
     * @return 权限值
     */
    @Override
    public List<String> getPermissionValueByUserId(Long userId) {
        List<String> permissionValueList;
        if (isSystemAdmin(userId)) {
            permissionValueList = baseMapper.selectAllPermissionValue();
        } else {
            permissionValueList = baseMapper.selectPermissionValueByUserId(userId);
        }
        return new ArrayList<>(permissionValueList);
    }

    /**
     * 判断用户是否为系统管理员
     *
     * @param userId 用户id
     * @return 是否为超级系统管理员
     */
    private Boolean isSystemAdmin(Long userId) {
        User user = userService.getById(userId);
        return user != null && user.getUsername().equals("admin");
    }

    /**
     * 获取所有的权限信息
     *
     * @return 所有的权限信息的集合
     */
    private List<Permission> getAllPermissionList() {
        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        // 按排序获取权限
        wrapper.orderByAsc(Permission::getSort);
        return baseMapper.selectList(wrapper);
    }

    /**
     * 获取子权限的id合集
     *
     * @param parentId       父权限id
     * @param permissionList 所有权限的列表集合
     * @return 子权限id集合(包括子子...权限)
     */
    private static List<Long> getChildIdList(Long parentId, List<Permission> permissionList) {
        List<Long> childIdList = new ArrayList<>();
        List<Permission> childPermissionList = permissionList.stream().filter(permission -> Objects.equals(permission.getPid(), parentId))
                .collect(Collectors.toList());
        childPermissionList.forEach(item -> {
            childIdList.add(item.getId());
            // 子权限的子权限id合集
            List<Long> childChildIdList = getChildIdList(item.getId(), permissionList);
            childIdList.addAll(childChildIdList);
        });
        return childIdList;
    }

    /**
     * 将permissionList中的多个permission按父子关系封装为一个permission
     *
     * @param permissionList 等待封装的permission列表
     * @return 封装完成的, 最顶层的权限实体
     */
    private static Permission buildPermission(List<Permission> permissionList) {
        // 先查找到名为全部权限的那一条权限记录(对应id为1,pid为0)
        Permission parent = null;
        for (Permission permission : permissionList) {
            if (permission.getPid().equals(0L)) {
                parent = permission;
                break;
            }
        }
        // 然后查找其子节点,并封装
        assert parent != null;
        parent.setLevel(0);
        parent.setChildren(findChildren(parent, permissionList));
        return parent;
    }

    /**
     * buildPermission的辅助函数
     *
     * @param parent         父节点
     * @param permissionList 待查找的permission列表
     * @return parentId对应节点的子节点
     */
    private static List<Permission> findChildren(Permission parent, List<Permission> permissionList) {
        List<Permission> children = new ArrayList<>();
        Integer currentLevel = parent.getLevel() + 1;
        for (Permission permission : permissionList) {
            if (permission.getPid().equals(parent.getId())) {
                // 如果permission的父id为parentId,则说明permission此时为parentId对应的节点的子节点
                children.add(permission);
                // 设置当前层级
                permission.setLevel(currentLevel);
                // permission还需要查找自己是否有子节点
                List<Permission> permissionChildren = findChildren(permission, permissionList);
                // permission设置自己的子节点
                permission.setChildren(permissionChildren);
            }
        }
        return children;
    }


    public static List<JSONObject> buildMenu(Permission topNode) {
        List<JSONObject> menus = new ArrayList<>();
        // 左侧一级菜单
        List<Permission> oneMenuList = topNode.getChildren();
        for (Permission one : oneMenuList) {
            JSONObject oneMenu = new JSONObject();
            oneMenu.put("path", one.getPath());
            oneMenu.put("component", one.getComponent());
            oneMenu.put("redirect", one.getRedirect());
            oneMenu.put("name", "name_" + one.getId());
            oneMenu.put("hidden", one.getHidden());

            JSONObject oneMeta = new JSONObject();
            oneMeta.put("title", one.getName());
            oneMeta.put("icon", one.getIcon());
            oneMenu.put("meta", oneMeta);

            List<JSONObject> children = new ArrayList<>();
            List<Permission> twoMenuList = one.getChildren();
            for (Permission two : twoMenuList) {
                // 二级和三级的菜单都是一级菜单下面
                addPermissionToList(children, two);

                List<Permission> threeMenuList = two.getChildren();
                for (Permission three : threeMenuList) {
                    if (StringUtils.isEmpty(three.getPath())) continue;
                    // 二级和三级的菜单都是一级菜单下面
                    addPermissionToList(children, three);
                }
            }
            oneMenu.put("children", children);
            menus.add(oneMenu);
        }

        return menus;
    }

    // 将permission加入到children中
    private static void addPermissionToList(List<JSONObject> children, Permission permission) {
        JSONObject threeMenu = new JSONObject();
        threeMenu.put("path", permission.getPath());
        threeMenu.put("component", permission.getComponent());
        threeMenu.put("name", "name_" + permission.getId());
        threeMenu.put("hidden", permission.getHidden());

        JSONObject threeMeta = new JSONObject();
        threeMeta.put("title", permission.getName());
        threeMeta.put("icon", permission.getIcon());
        threeMenu.put("meta", threeMeta);

        children.add(threeMenu);
    }

    /**
     * 设置权限是否被选中
     *
     * @param currentPermission 当前权限
     * @return 子权限是否完全被选中
     */
    private static boolean setSelectPermission(Permission currentPermission) {
        // 如果没有子节点,则查看是否被选择
        if (currentPermission.getChildren() == null || currentPermission.getChildren().isEmpty()) {
            return currentPermission.isSelect();
        }
        // 否则则查看子节点
        boolean isChildrenAllSelect = true;
        for (Permission childPermission : currentPermission.getChildren()) {
            if (!setSelectPermission(childPermission)) {
                // 如果子节点没有被选择则设置flag
                isChildrenAllSelect = false;
            }
        }
        // 设置本节点,并返回
        currentPermission.setSelect(isChildrenAllSelect);
        return isChildrenAllSelect;
    }
}
