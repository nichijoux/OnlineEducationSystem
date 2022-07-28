package com.zh.oes.auth.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.oes.model.entity.auth.Permission;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
public interface PermissionService extends IService<Permission> {
    // 获取所有的权限信息
    Permission getAllPermission();

    // 根据角色id获取角色的权限信息
    Permission getRolePermission(Long roleId);

    // 给roleId的角色分配权限
    void assignRolePermission(Long roleId, Long[] permissionIdList);

    // 递归删除权限菜单
    boolean deletePermissionById(Long permissionId);

    // 根据用户id获取动态菜单
    List<JSONObject> getPermissionByUserId(Long id);

    // 根据用户id获取权限值
    List<String> getPermissionValueByUserId(Long id);
}
