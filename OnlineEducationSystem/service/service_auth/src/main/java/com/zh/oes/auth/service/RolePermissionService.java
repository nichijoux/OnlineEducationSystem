package com.zh.oes.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.oes.model.entity.auth.RolePermission;

/**
 * <p>
 * 角色权限 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
public interface RolePermissionService extends IService<RolePermission> {

    // 禁用或者启用用户权限
    void enableOrDisableRolePermission(Long roleId, Boolean isEnable);
}
