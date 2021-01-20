package com.hawk.oauth.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.hawk.oauth.bean.JwtUser;
import com.hawk.oauth.persistence.entity.SysUser;
import com.hawk.oauth.persistence.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

/**
 * @Title: UserDetailsServiceImpl
 * @ProjectName spring-cloud-oauth2-demo
 * @Author Hawk
 * @Date 2021/1/8 13:54
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private ClientDetailsService clientDetailsService;

    @Resource
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StrUtil.isBlank(username)) {
            return null;
        }

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null) {
//            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
//            if (clientDetails != null) {
//                //密码
//                String clientSecret = clientDetails.getClientSecret();
//                return new User(username, clientSecret, AuthorityUtils.commaSeparatedStringToAuthorityList(""));
//            }
//        }

//        Map<String,Object> map = JSON.parseObject(username,Map.class);
//        username = (String)map.get("username");

        SysUser sysUser = sysUserService.selectByName(username);
        JwtUser jwtUser = null;
        // 账户冻结
        if (sysUser.getStatus() == 0) {
            jwtUser = new JwtUser(username, sysUser.getPassword(),
                    true,
                    true,
                    true,
                    false,
                    null);
        } else {
            Set<String> permissions = sysUserService.findPermissions(sysUser.getId());
            // 权限暂时测试加入测试权限
            if (permissions.size() == 0) {
                permissions.add("sys:dept:view");
            }

            String user_permission_string = StringUtils.join(permissions.toArray(), ",");
            jwtUser = new JwtUser(username, sysUser.getPassword(),
                    AuthorityUtils.commaSeparatedStringToAuthorityList(user_permission_string));
            jwtUser.setId(sysUser.getId().toString());
            jwtUser.setAvatar(sysUser.getAvatar());
            jwtUser.setName(sysUser.getNickName());
            jwtUser.setMobile(sysUser.getMobile());
            jwtUser.setStatus(sysUser.getStatus());
        }

        return jwtUser;
    }

    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        if (StrUtil.isBlank(mobile)) {
            return null;
        }

        SysUser sysUser = sysUserService.selectByMobile(mobile);
        JwtUser jwtUser = null;
        if (sysUser.getStatus() == 0) {
            jwtUser = new JwtUser(sysUser.getName(), sysUser.getPassword(),
                    true,
                    true,
                    true,
                    false,
                    null);
        } else {
            Set<String> permissions = sysUserService.findPermissions(sysUser.getId());
            // 权限暂时测试加入测试权限
            if (permissions.size() == 0) {
                permissions.add("sys:dept:view");
            }

            String user_permission_string = StringUtils.join(permissions.toArray(), ",");
            jwtUser = new JwtUser(sysUser.getName(), sysUser.getPassword(),
                    AuthorityUtils.commaSeparatedStringToAuthorityList(user_permission_string));
            jwtUser.setId(sysUser.getId().toString());
            jwtUser.setAvatar(sysUser.getAvatar());
            jwtUser.setName(sysUser.getNickName());
            jwtUser.setMobile(sysUser.getMobile());
            jwtUser.setStatus(sysUser.getStatus());
        }

        return jwtUser;
    }
}