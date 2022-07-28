package com.zh.oes.auth.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.oes.model.entity.auth.User;
import com.zh.oes.model.vo.auth.UserQueryCondition;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
public interface UserService extends IService<User> {
    // 分页查询用户信息
    Page<User> pageQueryUser(Long index, Long limit, UserQueryCondition queryCondition);

    // 根据用户名获取用户
    User getByUsername(String username);
}
