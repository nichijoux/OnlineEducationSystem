package com.zh.oes.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.oes.model.entity.auth.Permission;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    // 根据用户名获取权限菜单
    List<Permission> selectPermissionListByUserId(Long userId);

    // 获取所有的权限值
    List<String> selectAllPermissionValue();

    // 根据用户id获取权限值
    List<String> selectPermissionValueByUserId(Long userId);
}
