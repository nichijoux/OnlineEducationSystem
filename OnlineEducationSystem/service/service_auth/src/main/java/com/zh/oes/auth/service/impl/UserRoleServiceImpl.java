package com.zh.oes.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.oes.auth.mapper.UserRoleMapper;
import com.zh.oes.auth.service.UserRoleService;
import com.zh.oes.model.entity.auth.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    /**
     * 启用或者禁用用户id为userId的角色
     *
     * @param userId   用户id
     * @param isEnable 是否启用
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void enableOrDisableUserRole(Long userId, Boolean isEnable) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Objects.nonNull(userId), UserRole::getUserId, userId);

        UserRole userRole = new UserRole();
        userRole.setIsEnable(isEnable);
        baseMapper.update(userRole, wrapper);
    }
}
