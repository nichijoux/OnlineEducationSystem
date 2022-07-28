package com.zh.oes.auth.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.oes.auth.service.RoleService;
import com.zh.oes.common.utils.Result;
import com.zh.oes.model.entity.auth.Role;
import com.zh.oes.model.vo.auth.RoleQueryCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色后台 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
@Api(tags = "角色后台api接口")
@RestController
@RequestMapping("admin/auth/role")
public class RoleController {
    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation(value = "分页查询角色信息")
    @PostMapping("pageQueryRole/{index}/{limit}")
    public Result pageQueryRole(
            @ApiParam(name = "index", value = "当前页", required = true)
            @PathVariable("index") Long index,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable("limit") Long limit,
            @RequestBody(required = false) RoleQueryCondition queryCondition) {
        Page<Role> roleList = roleService.pageQueryRole(index, limit, queryCondition);
        return Result.success(roleList);
    }

    @ApiOperation(value = "根据角色id获取角色")
    @GetMapping("getRole/{roleId}")
    public Result getRole(
            @ApiParam(name = "roleId", value = "角色id", required = true)
            @PathVariable("roleId") Long roleId) {
        Role role = roleService.getById(roleId);
        return Result.success(role);
    }

    @ApiOperation(value = "增加新的角色")
    @PostMapping("addRole")
    public Result addRole(
            @ApiParam(name = "role", value = "要增加的角色信息", required = true)
            @Validated @RequestBody Role role) {
        return roleService.save(role) ?
                Result.success() : Result.failure().message("新增角色失败");
    }

    @ApiOperation(value = "修改角色信息")
    @PostMapping("updateRole")
    public Result updateRole(
            @ApiParam(name = "role", value = "修改的角色信息", required = true)
            @Validated @RequestBody Role role) {
        return roleService.updateById(role) ?
                Result.success() : Result.failure().message("修改角色信息失败");
    }

    @ApiOperation(value = "根据id删除角色")
    @DeleteMapping("deleteRole/{roleId}")
    public Result deleteRole(
            @ApiParam(name = "roleId", value = "要删除的角色id", required = true)
            @PathVariable Long roleId) {
        return roleService.removeById(roleId) ?
                Result.success() : Result.failure().message("删除角色失败");
    }

    @ApiOperation(value = "根据id批量删除角色")
    @DeleteMapping("batchDeleteRole")
    public Result batchDeleteRole(
            @ApiParam(name = "idList", value = "要删除的角色id列表", required = true)
            @RequestBody List<Long> idList) {
        return roleService.removeByIds(idList) ?
                Result.success() : Result.failure().message("批量删除角色失败");
    }
}

