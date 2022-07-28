package com.zh.oes.auth.controller;


import com.zh.oes.auth.service.PermissionService;
import com.zh.oes.common.utils.Result;
import com.zh.oes.model.entity.auth.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
@Api(tags = "权限后台api接口")
@RestController
@RequestMapping("admin/auth/permission")
public class PermissionController {
    private PermissionService permissionService;

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @ApiOperation(value = "获取所有的权限菜单")
    @GetMapping("getAllPermission")
    public Result getAllPermission() {
        Permission permission = permissionService.getAllPermission();
        // element-ui的el-table需要的是数组对象,因此转为List<Permission>
        return Result.success(new ArrayList<>(Collections.singleton(permission)));
    }

    @ApiOperation(value = "根据角色id获取角色的权限")
    @GetMapping("getRolePermission/{roleId}")
    public Result getRolePermission(
            @ApiParam(name = "roleId", value = "角色id", required = true)
            @PathVariable Long roleId) {
        Permission permission = permissionService.getRolePermission(roleId);
        // element-ui需要array数组
        return Result.success(new ArrayList<>(Collections.singleton(permission)));
    }

    @ApiOperation(value = "给roleId的角色分配权限")
    @PostMapping("assignRolePermission")
    public Result assignRolePermission(
            @ApiParam(name = "roleId", value = "角色id", required = true)
            @RequestParam Long roleId,
            @ApiParam(name = "permissionIdList", value = "要分配的权限id合集", required = true)
            @RequestParam Long[] permissionIdList) {
        permissionService.assignRolePermission(roleId, permissionIdList);
        return Result.success();
    }

    @ApiOperation(value = "添加新的权限菜单")
    @PostMapping("addPermission")
    public Result addPermission(
            @ApiParam(name = "permission", value = "新增菜单的信息", required = true)
            @Validated @RequestBody Permission permission) {
        return permissionService.save(permission) ?
                Result.success() : Result.failure().message("新增权限菜单失败");
    }

    @ApiOperation(value = "更新权限菜单")
    @PostMapping("updatePermission")
    public Result updatePermission(
            @ApiParam(name = "permission", value = "更新菜单的信息", required = true)
            @Validated @RequestBody Permission permission) {
        if (permission.getId().equals(1L)) {
            return Result.failure().message("不可修改该权限");
        }
        return permissionService.updateById(permission) ?
                Result.success() : Result.failure().message("更新权限菜单失败");
    }

    @ApiOperation(value = "递归删除权限菜单")
    @DeleteMapping("deletePermission/{permissionId}")
    public Result deletePermission(
            @ApiParam(name = "permissionId", value = "要删除的权限菜单id", required = true)
            @PathVariable Long permissionId) {
        if (permissionId.equals(1L)) {
            return Result.failure().message("不可删除该权限");
        }
        return permissionService.deletePermissionById(permissionId) ?
                Result.success() : Result.failure().message("递归删除权限菜单失败");
    }
}

