package com.zh.oes.auth.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.oes.model.entity.auth.Role;
import com.zh.oes.model.vo.auth.RoleQueryCondition;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
public interface RoleService extends IService<Role> {
    // 分页条件查询角色信息
    Page<Role> pageQueryRole(Long index, Long limit, RoleQueryCondition queryCondition);

    // 根据用户id获取用户角色
    Map<String, Object> getRoleByUserId(Long userId);

    // 根据用户id分配用户角色
    boolean assignRole(Long userId, Long[] roleIdList);

    // 获取角色列表
    List<Role> getRoleListByUserId(Long userId);
}
