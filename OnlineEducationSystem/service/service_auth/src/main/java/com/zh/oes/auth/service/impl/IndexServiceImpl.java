package com.zh.oes.auth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zh.oes.auth.service.IndexService;
import com.zh.oes.auth.service.PermissionService;
import com.zh.oes.auth.service.RoleService;
import com.zh.oes.auth.service.UserService;
import com.zh.oes.model.entity.auth.Role;
import com.zh.oes.model.entity.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IndexServiceImpl implements IndexService {
    private UserService userService;

    private RoleService roleService;

    private PermissionService permissionService;

    private RedisTemplate<String, List<String>> redisTemplate;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, List<String>> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 根据用户名获取菜单
     *
     * @param username 用户名
     * @return 菜单
     */
    @Override
    public List<JSONObject> getMenu(String username) {
        User user = userService.getByUsername(username);
        // 根据用户id获取用户菜单权限
        return permissionService.getPermissionByUserId(user.getId());
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    @Override
    public Map<String, Object> getUserInfo(String username) {
        Map<String, Object> result = new HashMap<>();
        User user = userService.getByUsername(username);

        // 根据用户id获取角色
        List<Role> roleList = roleService.getRoleListByUserId(user.getId());
        // 把获得的所有角色名称放在List集合中
        List<String> roleNameList = roleList.stream().filter(Role::getIsEnable).map(Role::getRoleName)
                .collect(Collectors.toList());
        // admin就没有角色，但是admin可以获取所有权限
        if (roleNameList.size() == 0) {
            //前端框架必须返回一个角色，否则报错，如果没有角色，返回一个空角色
            roleNameList.add("admin");
        }

        //根据用户id获取操作权限值
        List<String> permissionValueList = permissionService.getPermissionValueByUserId(user.getId());
        // 更新用户在redis中存储的信息,没必要,毕竟在TokenLoginFilter.java中刚添加,没必要更新
        redisTemplate.opsForValue().set(username, permissionValueList);

        result.put("name", user.getUsername());
        result.put("avatar", "https://avatars.githubusercontent.com/u/58805009?v=4");
        result.put("roles", roleNameList);
        // 权限值列表
        result.put("permissionValueList", permissionValueList);
        return result;
    }
}
