package com.zh.oes.auth.service.impl;

import com.zh.oes.auth.service.PermissionService;
import com.zh.oes.auth.service.UserService;
import com.zh.oes.common.base.exception.OESException;
import com.zh.oes.common.security.entity.SecurityUser;
import com.zh.oes.model.entity.auth.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserService userService;

    private PermissionService permissionService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        User user = userService.getByUsername(username);

        // 判断用户是否存在
        if (null == user) {
            throw new UsernameNotFoundException("用户名不存在！");
        } else if (!user.getIsEnable()) {
            throw new OESException("用户被禁用");
        }
        // 返回UserDetails实现类
        com.zh.oes.common.security.entity.User curUser = new com.zh.oes.common.security.entity.User();
        BeanUtils.copyProperties(user, curUser);
        // 获取用户的权限列表
        List<String> authorities = permissionService.getPermissionValueByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser(curUser);
        securityUser.setPermissionValueList(authorities);
        return securityUser;
    }
}
