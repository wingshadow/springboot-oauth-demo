package com.hawk.oauth.controller;

import com.hawk.oauth.bean.AuthorityToken;
import com.hawk.oauth.service.CustomAuthorityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Title: LoginController
 * @ProjectName springboot-oauth2-jwt
 * @Author Hawk
 * @Date 2021/1/19 14:39
 */
@RestController
public class LoginController {

    @Resource
    private CustomAuthorityService customAuthorityService;

    @PostMapping(value = "/login")
    public AuthorityToken login(HttpServletRequest request, String username, String password) {
        String authorization = request.getHeader("Authorization");
        return customAuthorityService.login(authorization, username, password, "password");
    }

    @PostMapping(value = "/loginBySmsCode")
    public AuthorityToken loginBySmsCode(HttpServletRequest request, String mobile, String smsCode) {
        String authorization = request.getHeader("Authorization");
        return customAuthorityService.login(authorization, mobile, smsCode, "mobile");
    }

    @PostMapping(value = "/refreshToken")
    public AuthorityToken login(HttpServletRequest request,String refreshToken) {
        String authorization = request.getHeader("Authorization");
        return customAuthorityService.refreshToken(authorization, refreshToken);
    }
}
