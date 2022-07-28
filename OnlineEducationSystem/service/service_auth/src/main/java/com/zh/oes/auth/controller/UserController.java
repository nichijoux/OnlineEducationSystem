package com.zh.oes.auth.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.oes.auth.service.RoleService;
import com.zh.oes.auth.service.UserService;
import com.zh.oes.common.utils.MD5Util;
import com.zh.oes.common.utils.Result;
import com.zh.oes.model.entity.auth.User;
import com.zh.oes.model.vo.auth.UserQueryCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
@Api(tags = "用户后台api接口")
@RestController
@RequestMapping("admin/auth/user")
public class UserController {
    private UserService userService;

    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("pageQueryUser/{index}/{limit}")
    public Result pageQueryUser(
            @ApiParam(name = "index", value = "当前页", required = true)
            @PathVariable Long index,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "queryCondition", value = "用户查询对象")
            @RequestBody(required = false) UserQueryCondition queryCondition) {
        Page<User> userList = userService.pageQueryUser(index, limit, queryCondition);
        return Result.success(userList);
    }

    @ApiOperation(value = "根据id查询user信息")
    @GetMapping("getUser/{userId}")
    public Result getUser(
            @ApiParam(name = "userId", value = "要查询的用户id", required = true)
            @PathVariable Long userId) {
        User user = userService.getById(userId);
        return Result.success(user);
    }

    @ApiOperation(value = "添加管理用户")
    @PostMapping("addUser")
    public Result addUser(
            @ApiParam(name = "user", value = "要添加的用户信息", required = true)
            @Validated @RequestBody User user) {
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        return userService.save(user) ?
                Result.success() : Result.failure().message("添加管理用户失败");
    }

    @ApiOperation(value = "更新管理用户信息")
    @PostMapping("updateUser")
    public Result updateUser(
            @ApiParam(name = "user", value = "要修改的用户信息", required = true)
            @Validated @RequestBody User user) {
        return userService.updateById(user) ?
                Result.success() : Result.failure().message("修改管理用户失败");
    }

    @ApiOperation(value = "根据id删除管理用户")
    @DeleteMapping("deleteUser/{userId}")
    public Result deleteUser(
            @ApiParam(name = "userId", value = "要删除的用户id", required = true)
            @PathVariable Long userId) {
        User user = userService.getById(userId);
        if (user.getUsername().equals("admin")) {
            return Result.failure().message("admin用户不能被删除");
        }
        return userService.removeById(userId) ?
                Result.success() : Result.failure().message("删除后台用户失败");
    }

    @ApiOperation(value = "根据id列表批量删除管理用户")
    @DeleteMapping("batchDeleteUser")
    public Result batchDeleteUser(
            @ApiParam(name = "userIdList", value = "要删除的用户的id列表", required = true)
            @RequestBody List<Long> userIdList) {
        return userService.removeByIds(userIdList) ?
                Result.success() : Result.failure().message("批量删除后台用户失败");
    }

    @ApiOperation(value = "根据用户id获取用户的角色")
    @GetMapping("getUserRole/{userId}")
    public Result getUserRole(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @PathVariable Long userId) {
        Map<String, Object> roleMap = roleService.getRoleByUserId(userId);
        return Result.success(roleMap);
    }

    @ApiOperation(value = "为用户分配角色")
    @PostMapping("assignRole")
    public Result assignRole(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam("userId") Long userId,
            @ApiParam(name = "roleIdList", value = "角色列表", required = true)
            @RequestParam("roleIdList") Long[] roleIdList) {
        return roleService.assignRole(userId, roleIdList) ?
                Result.success() : Result.failure().message("角色分配失败");
    }
}

