package com.zh.oes.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.oes.auth.mapper.RolePermissionMapper;
import com.zh.oes.auth.service.RolePermissionService;
import com.zh.oes.model.entity.auth.RolePermission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * <p>
 * 角色权限 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {
    /**
     * 启用或者禁用角色id为roleId的角色权限
     *
     * @param roleId   要更新的角色id
     * @param isEnable 是否启用或者禁用
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void enableOrDisableRolePermission(Long roleId, Boolean isEnable) {
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Objects.nonNull(roleId), RolePermission::getRoleId, roleId);

        RolePermission rolePermission = new RolePermission();
        rolePermission.setIsEnable(isEnable);
        baseMapper.update(rolePermission, wrapper);
    }
}
