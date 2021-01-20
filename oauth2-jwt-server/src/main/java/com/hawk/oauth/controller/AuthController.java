package com.hawk.oauth.controller;

import com.hawk.oauth.bean.AuthToken;
import com.hawk.oauth.bean.LoginRequest;
import com.hawk.oauth.bean.RefreshRequest;
import com.hawk.oauth.bean.SmsRequest;
import com.hawk.oauth.service.AuthService;
import com.hawk.oauth.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Title: AuthController
 * @ProjectName spring-cloud-oauth2-demo
 * @Author Hawk
 * @Date 2021/1/8 15:08
 */
@Slf4j
@RestController
public class AuthController {

    @Value("${auth.clientId}")
    private String clientId;
    @Value("${auth.clientSecret}")
    private String clientSecret;
    @Value("${auth.cookieDomain}")
    private String cookieDomain;
    @Value("${auth.cookieMaxAge}")
    private int cookieMaxAge;

    @Resource
    private AuthService authService;

    @PostMapping("/login")
    public String login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        Integer type = loginRequest.getType();

        AuthToken authToken = authService.login(username, password, "password",
                type, clientId, clientSecret);

        String jti = authToken.getJti();
        //将令牌存储到cookie
        this.saveCookie(jti);
        return jti;
    }

    @PostMapping(value = "/testGetToken")
    public OAuth2AccessToken testGetToken(HttpServletRequest request){

      return  authService.getToken("XcWebApp","machao","123456");
//        return null;
    }

    @PostMapping(value = "/refreshToken")
    public String refreshToken(RefreshRequest refreshRequest) {
        AuthToken authToken = authService.refreshToken(refreshRequest.getRefreshToken(), clientId, clientSecret);

        String jti = authToken.getJti();
        //将令牌存储到cookie
        this.saveCookie(jti);
        return jti;
    }

    @PostMapping(value = "/loginByMobile")
    public String loginByMobile(SmsRequest smsRequest) {

        AuthToken authToken = authService.loginByMobile(smsRequest.getMobile(), smsRequest.getCode(), "mobile",
                clientId,
                clientSecret);

        String jti = authToken.getJti();
        //将令牌存储到cookie
        this.saveCookie(jti);
        return jti;
    }

    private void saveCookie(String token) {
        HttpServletResponse response =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response, cookieDomain, "/", "uid", token, cookieMaxAge, false);

    }

    private void clearCookie(String token) {
        HttpServletResponse response =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response, cookieDomain, "/", "uid", token, 0, false);
    }

    @PostMapping("/logout")
    public String logout() {
        String uid = getTokenFormCookie();
        boolean result = authService.delToken(uid);
        this.clearCookie(uid);
        return "ok";
    }

    @GetMapping("/getToken")
    public String getToken(@RequestParam String jti) {
        String uid = getTokenFormCookie();
//        if(uid == null){
//            return "fail";
//        }
        uid = jti;
        //拿身份令牌从redis中查询jwt令牌
        AuthToken userToken = authService.getUserToken(uid);
        if (userToken != null) {
            //将jwt令牌返回给用户
            String accessToken = userToken.getAccessToken();
            return accessToken;
        }
        return null;
    }

    private String getTokenFormCookie() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> map = CookieUtil.readCookie(request, "uid");
        if (map != null && map.get("uid") != null) {
            String uid = map.get("uid");
            return uid;
        }
        return null;
    }


}
