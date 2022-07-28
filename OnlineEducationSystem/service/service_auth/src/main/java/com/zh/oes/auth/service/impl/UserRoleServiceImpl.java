package com.zh.oes.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.oes.auth.mapper.UserRoleMapper;
import com.zh.oes.auth.service.UserRoleService;
import com.zh.oes.model.entity.auth.UserRole;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
