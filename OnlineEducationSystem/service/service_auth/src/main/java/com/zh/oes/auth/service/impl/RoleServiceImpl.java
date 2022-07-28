package com.zh.oes.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.oes.auth.mapper.RoleMapper;
import com.zh.oes.auth.service.RoleService;
import com.zh.oes.auth.service.UserRoleService;
import com.zh.oes.model.entity.auth.Role;
import com.zh.oes.model.entity.auth.UserRole;
import com.zh.oes.model.vo.auth.RoleQueryCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    private UserRoleService userRoleService;

    @Autowired
    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    /**
     * 分页条件查询,获取角色信息
     *
     * @param index          当前页
     * @param limit          每页记录数
     * @param queryCondition 查询条件
     * @return 查询到的角色列表信息
     */
    @Override
    public Page<Role> pageQueryRole(Long index, Long limit, RoleQueryCondition queryCondition) {
        Page<Role> rolePage = new Page<>(index, limit);
        if (queryCondition == null) {
            return baseMapper.selectPage(rolePage, null);
        }
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();

        String name = queryCondition.getName();
        wrapper.like(StringUtils.hasText(name), Role::getRoleName, name);

        return baseMapper.selectPage(rolePage, wrapper);
    }

    /**
     * 根据用户id获取用户角色
     *
     * @param userId 用户id
     * @return 用户角色
     */
    @Override
    public Map<String, Object> getRoleByUserId(Long userId) {
        // 查询所有的角色
        List<Role> allRolesList = baseMapper.selectList(null);

        // 根据用户id，查询用户拥有的角色id
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId, userId);
        wrapper.select(UserRole::getRoleId);
        List<UserRole> existUserRoleList = userRoleService.list(wrapper);

        List<Long> existRoleList = existUserRoleList.stream().map(UserRole::getRoleId)
                .collect(Collectors.toList());

        //对角色进行分类
        List<Role> assignRoles = new ArrayList<>();
        for (Role role : allRolesList) {
            //已分配
            if (existRoleList.contains(role.getId())) {
                assignRoles.add(role);
            }
        }

        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("assignRoles", assignRoles);
        roleMap.put("allRolesList", allRolesList);
        return roleMap;
    }

    /**
     * 为用户分配角色
     *
     * @param userId     用户id
     * @param roleIdList 用户角色列表
     * @return 是否分配成功
     */
    @Override
    public boolean assignRole(Long userId, Long[] roleIdList) {
        // 先把已有角色删除
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId, userId);
        userRoleService.remove(wrapper);

        // 将角色id转换为角色
        List<UserRole> userRoleList = new ArrayList<>();
        for (Long roleId : roleIdList) {
            if (Objects.isNull(roleId)) continue;
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);

            userRoleList.add(userRole);
        }
        return userRoleService.saveBatch(userRoleList);
    }

    /**
     * 获取角色列表
     *
     * @param userId 用户id
     * @return 角色列表
     */
    @Override
    public List<Role> getRoleListByUserId(Long userId) {
        //根据用户id拥有的角色id
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId, userId);
        wrapper.select(UserRole::getRoleId);
        List<UserRole> userRoleList = userRoleService.list(wrapper);
        List<Long> roleIdList = userRoleList.stream().map(UserRole::getRoleId)
                .collect(Collectors.toList());
        List<Role> roleList = new ArrayList<>();
        if (roleIdList.size() > 0) {
            roleList = baseMapper.selectBatchIds(roleIdList);
        }
        return roleList;
    }
}
