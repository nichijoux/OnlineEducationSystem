package com.zh.oes.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.oes.auth.mapper.UserMapper;
import com.zh.oes.auth.service.UserRoleService;
import com.zh.oes.auth.service.UserService;
import com.zh.oes.model.entity.auth.User;
import com.zh.oes.model.vo.auth.UserQueryCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private UserRoleService userRoleService;

    @Autowired
    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    /**
     * 分页查询用户信息
     *
     * @param index          当前页
     * @param limit          每页记录数
     * @param queryCondition 查询条件
     * @return 查询到的用户列表
     */
    @Override
    public Page<User> pageQueryUser(Long index, Long limit, UserQueryCondition queryCondition) {
        Page<User> userPage = new Page<>(index, limit);
        if (queryCondition == null) {
            return baseMapper.selectPage(userPage, null);
        }

        // 获取查询条件
        String name = queryCondition.getName();
        String beginTime = queryCondition.getBeginTime();
        String endTime = queryCondition.getEndTime();
        // 设定查询条件
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(name), User::getUsername, name);
        wrapper.ge(StringUtils.hasText(beginTime), User::getCreateTime, beginTime);
        wrapper.le(StringUtils.hasText(endTime), User::getCreateTime, endTime);

        return baseMapper.selectPage(userPage, wrapper);
    }

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @return 用户
     */
    @Override
    public User getByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        wrapper.eq(User::getIsEnable, true);
        return baseMapper.selectOne(wrapper);
    }

    /**
     * 根据角色id启用或者禁用某个用户
     *
     * @param userId   用户id
     * @param isEnable 是否启用用户
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void enableOrDisableUser(Long userId, Boolean isEnable) {
        // 更新user表
        User user = new User();
        user.setIsEnable(isEnable);
        user.setId(userId);
        baseMapper.updateById(user);
        // 更新userRole表
        userRoleService.enableOrDisableUserRole(userId, isEnable);
    }
}
