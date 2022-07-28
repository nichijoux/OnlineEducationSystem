package com.zh.oes.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.oes.auth.mapper.RolePermissionMapper;
import com.zh.oes.auth.service.RolePermissionService;
import com.zh.oes.model.entity.auth.RolePermission;
import org.springframework.stereotype.Service;

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

}
