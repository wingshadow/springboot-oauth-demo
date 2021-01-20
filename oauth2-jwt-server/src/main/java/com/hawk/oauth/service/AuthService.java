package com.hawk.oauth.service;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.hawk.oauth.bean.AuthToken;
import com.hawk.oauth.exception.GlobalException;
import com.hawk.oauth.redis.RedisKeys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestValidator;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Title: AuthService
 * @ProjectName spring-cloud-oauth2-demo
 * @Author Hawk
 * @Date 2021/1/8 19:25
 */
@Slf4j
@Service
public class AuthService {

    @Value("${auth.tokenValiditySeconds}")
    private int tokenValiditySeconds;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private TokenGranter tokenGranter;

    @Resource
    private ClientDetailsService clientDetailsService;

    private OAuth2RequestValidator oAuth2RequestValidator = new DefaultOAuth2RequestValidator();

    /**
     * 登陆验证并返回token
     *
     * @param username
     * @param password
     * @param clientId
     * @param clientSecret
     * @return
     */
    public AuthToken login(String username, String password, String grantType,int type, String clientId,
                           String clientSecret) {

        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("type", type);

        //定义body
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", grantType);
        body.add("username", JSON.toJSONString(map));
        body.add("password", password);

        //请求spring security申请令牌
        AuthToken authToken = this.applyToken(body, clientId, clientSecret);
        // 缓存token
        if (!cacheToken(authToken)) {
            return null;
        }
        return authToken;

    }

    public AuthToken loginByMobile(String mobile, String code, String type, String clientId, String clientSecret) {
        //定义body
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", type);
        body.add("mobile", mobile);
        body.add("code", code);

        //请求spring security申请令牌
        AuthToken authToken = this.applyToken(body, clientId, clientSecret);
        // 缓存token
        if (!cacheToken(authToken)) {
            return null;
        }
        return authToken;

    }

    /**
     * 刷新token并返回token
     *
     * @param refreshToken
     * @param clientId
     * @param clientSecret
     * @return
     */
    public AuthToken refreshToken(String refreshToken, String clientId, String clientSecret) {
        //定义body
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "refresh_token");
        body.add("refresh_token", refreshToken);
        // 缓存token
        AuthToken authToken = this.applyToken(body, clientId, clientSecret);
        if (!cacheToken(authToken)) {
            return null;
        }
        return authToken;
    }

    /**
     * 缓存token
     *
     * @param token
     * @return
     */
    private boolean cacheToken(AuthToken token) {
        //用户身份令牌
        String jti = token.getJti();
        //存储到redis中的内容
        String jsonString = JSON.toJSONString(token);
        //将令牌存储到redis
        return this.saveToken(jti, jsonString, tokenValiditySeconds);
    }

    public boolean delToken(String jti) {
        String key = RedisKeys.LOGIN_TOKEN_PREFIX + jti;
        stringRedisTemplate.delete(key);
        return true;
    }

    public AuthToken getUserToken(String jti) {
        String key = RedisKeys.LOGIN_TOKEN_PREFIX + jti;
        //从redis中取到令牌信息
        String value = stringRedisTemplate.opsForValue().get(key);
        //转成对象
        try {
            AuthToken authToken = JSON.parseObject(value, AuthToken.class);
            return authToken;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param body
     * @param clientId
     * @param clientSecret
     * @return
     */
    private AuthToken applyToken(MultiValueMap<String, String> body, String clientId, String clientSecret) {


        String authUrl = "http://localhost:9091/oauth/token";

        LinkedMultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        String httpBasic = getHttpBasic(clientId, clientSecret);
        header.add("Authorization", httpBasic);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, header);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 400 && response.getRawStatusCode() != 401) {
                    super.handleError(response);
                }
            }
        });

        ResponseEntity<Map> exchange = restTemplate.exchange(authUrl, HttpMethod.POST, httpEntity, Map.class);
        if (exchange.getStatusCode().value() != 200) {
            throw new GlobalException(40001, "系统内部错误");
        }
        Map bodyMap = exchange.getBody();
        if (CollUtil.isEmpty(bodyMap)) {
            throw new GlobalException(40001, "系统内部错误");
        }

        AuthToken authToken = new AuthToken();
        authToken.setJti((String) bodyMap.get("jti"));
        authToken.setAccessToken((String) bodyMap.get("access_token"));
        authToken.setRefreshToken((String) bodyMap.get("refresh_token"));
        return authToken;
    }

    public OAuth2AccessToken getToken(String clientId,String username,String password){
        ClientDetails authenticatedClient = clientDetailsService.loadClientByClientId(clientId);

        Map<String,String> parameters = new HashMap<>();
        parameters.put("username",username);
        parameters.put("password",password);
        parameters.put("grant_type","password");
        TokenRequest tokenRequest = getOAuth2RequestFactory().createTokenRequest(parameters, authenticatedClient);

        if (clientId != null && !clientId.equals("")) {
            // Only validate the client details if a client authenticated during this
            // request.
            if (!clientId.equals(tokenRequest.getClientId())) {
                // double check to make sure that the client ID in the token request is the same as that in the
                // authenticated client
                throw new InvalidClientException("Given client ID does not match authenticated client");
            }
        }

        if (authenticatedClient != null) {
            oAuth2RequestValidator.validateScope(tokenRequest, authenticatedClient);
        }
        if (!StringUtils.hasText(tokenRequest.getGrantType())) {
            throw new InvalidRequestException("Missing grant type");
        }
        if (tokenRequest.getGrantType().equals("implicit")) {
            throw new InvalidGrantException("Implicit grant type not supported from token endpoint");
        }

        if (isAuthCodeRequest(parameters)) {
            // The scope was requested or determined during the authorization step
            if (!tokenRequest.getScope().isEmpty()) {
                tokenRequest.setScope(Collections.<String> emptySet());
            }
        }

        if (isRefreshTokenRequest(parameters)) {
            // A refresh token has its own default scopes, so we should ignore any added by the factory here.
            tokenRequest.setScope(OAuth2Utils.parseParameterList(parameters.get(OAuth2Utils.SCOPE)));
        }

        OAuth2AccessToken token = tokenGranter.grant(tokenRequest.getGrantType(), tokenRequest);
        if (token == null) {
            throw new UnsupportedGrantTypeException("Unsupported grant type: " + tokenRequest.getGrantType());
        }
        return token;
    }

    private boolean isAuthCodeRequest(Map<String, String> parameters) {
        return "authorization_code".equals(parameters.get("grant_type")) && parameters.get("code") != null;
    }

    private boolean isRefreshTokenRequest(Map<String, String> parameters) {
        return "refresh_token".equals(parameters.get("grant_type")) && parameters.get("refresh_token") != null;
    }

    private DefaultOAuth2RequestFactory getOAuth2RequestFactory(){
        return new DefaultOAuth2RequestFactory(clientDetailsService);
    }

    private TokenGranter getTokenGranter(){
        return null;
    }

    private boolean saveToken(String jti, String content, long ttl) {
        String key = RedisKeys.LOGIN_TOKEN_PREFIX + jti;
        stringRedisTemplate.boundValueOps(key).set(content, ttl, TimeUnit.SECONDS);
        Long expire = stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
        return expire > 0;
    }


    private String getHttpBasic(String clientId, String clientSecret) {
        String string = clientId + ":" + clientSecret;
        //将串进行base64编码
        byte[] encode = Base64Utils.encode(string.getBytes());
        return "Basic " + new String(encode);
    }
}
