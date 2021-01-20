package com.hawk.manage.filter;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.hawk.manage.bean.AuthorityToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: TokenFilter
 * @ProjectName springboot-oauth2-jwt
 * @Author Hawk
 * @Date 2021/1/20 9:32
 */
@Slf4j
//@Order(0)
//@Component
public class TokenFilter extends GenericFilterBean {

    private StringRedisTemplate stringRedisTemplate;

    public TokenFilter(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        log.info("filter doFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        String jti = req.getHeader("jti");
        MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(req);

        try {
            String tokenStr = stringRedisTemplate.opsForValue().get("USER_KEY:" + jti);
            if (StrUtil.isBlank(tokenStr)) {
                Map<String, Object> error = new HashMap<>();
                error.put("code", 20002);
                error.put("msg", "用户未登录");
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(JSON.toJSONString(error));
                return;
            }
            AuthorityToken authorityToken = JSON.parseObject(tokenStr, AuthorityToken.class);


            mutableRequest.addHeader("Authorization", "bearer " + authorityToken.getAccessToken());
            chain.doFilter(mutableRequest, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
