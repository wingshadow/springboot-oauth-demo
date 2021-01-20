package com.hawk.oauth.service;

import com.alibaba.fastjson.JSON;
import com.hawk.common.constraint.RedisKeys;
import com.hawk.oauth.bean.AuthorityToken;
import com.hawk.oauth.utils.CryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.*;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestValidator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Title: CustomAuthorityService
 * @ProjectName springboot-oauth2-jwt
 * @Author Hawk
 * @Date 2021/1/19 14:02
 */

@Service
public class CustomAuthorityService {

    @Value("${auth.tokenValiditySeconds}")
    private int tokenValiditySeconds;

    @Resource
    private TokenGranter tokenGranter;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private ClientDetailsService clientDetailsService;

    public AuthorityToken login(String authorization, String username, String password, String grantType) {
        try {
            String[] tokens = CryptUtils.decode(authorization);
            String clientId = tokens[0];
            String clientSecret = tokens[1];

            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

            if (clientDetails == null) {
                throw new UnapprovedClientAuthenticationException("Unknown client id : " + clientId);
            }

            if (!getBCryptPasswordEncoder().matches(clientSecret, clientDetails.getClientSecret())) {
                throw new UnapprovedClientAuthenticationException("Invalid client secret for client id : " + clientId);
            }

            Map<String, String> parameters = new HashMap<>();
            if ("password".equals(grantType)) {
                parameters.put("username", username);
                parameters.put("password", password);
            } else if ("mobile".equals(grantType)) {
                parameters.put("mobile", username);
                parameters.put("code", password);
            }
            parameters.put("grant_type", grantType);
            OAuth2AccessToken token = applyAccessToken(clientDetails, parameters);

            AuthorityToken authorityToken = new AuthorityToken();
            authorityToken.setAccessToken(token.getValue());
            authorityToken.setRefreshToken(token.getRefreshToken().getValue());
            authorityToken.setExpiryTime(token.getExpiresIn());
            authorityToken.setJti((String)token.getAdditionalInformation().get("jti"));

            // 保存token到redis
            saveToken(authorityToken.getJti(),JSON.toJSONString(authorityToken),tokenValiditySeconds);
            return authorityToken;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public AuthorityToken refreshToken(String authorization, String refreshToken) {
        try {
            String[] tokens = CryptUtils.decode(authorization);
            String clientId = tokens[0];
            String clientSecret = tokens[1];

            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

            if (clientDetails == null) {
                throw new UnapprovedClientAuthenticationException("Unknown client id : " + clientId);
            }

            if (!getBCryptPasswordEncoder().matches(clientSecret, clientDetails.getClientSecret())) {
                throw new UnapprovedClientAuthenticationException("Invalid client secret for client id : " + clientId);
            }
            Map<String, String> parameters = new HashMap<>();
            parameters.put("refresh_token",refreshToken);
            parameters.put("grant_type", "refresh_token");
            OAuth2AccessToken token = applyAccessToken(clientDetails, parameters);

            AuthorityToken authorityToken = new AuthorityToken();
            authorityToken.setAccessToken(token.getValue());
            authorityToken.setRefreshToken(token.getRefreshToken().getValue());
            authorityToken.setExpiryTime(token.getExpiresIn());
            authorityToken.setJti((String)token.getAdditionalInformation().get("jti"));
            return authorityToken;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private OAuth2AccessToken applyAccessToken(ClientDetails clientDetails, Map<String, String> parameters) {


        TokenRequest tokenRequest = getOAuth2RequestFactory().createTokenRequest(parameters, clientDetails);

        if (clientDetails != null) {
            oAuth2RequestValidator().validateScope(tokenRequest, clientDetails);
        }
        if (!StringUtils.hasText(tokenRequest.getGrantType())) {
            throw new InvalidRequestException("Missing grant type");
        }
        if (tokenRequest.getGrantType().equals("implicit")) {
            throw new InvalidGrantException("Implicit grant type not supported from token endpoint");
        }

        if (isAuthCodeRequest(parameters)) {
            if (!tokenRequest.getScope().isEmpty()) {
                tokenRequest.setScope(Collections.<String>emptySet());
            }
        }

        if (isRefreshTokenRequest(parameters)) {
            tokenRequest.setScope(OAuth2Utils.parseParameterList(parameters.get(OAuth2Utils.SCOPE)));
        }

        OAuth2AccessToken token = tokenGranter.grant(tokenRequest.getGrantType(), tokenRequest);
        if (token == null) {
            throw new UnsupportedGrantTypeException("Unsupported grant type: " + tokenRequest.getGrantType());
        }
        return token;
    }

    private OAuth2RequestValidator oAuth2RequestValidator() {
        return new DefaultOAuth2RequestValidator();
    }

    private BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private DefaultOAuth2RequestFactory getOAuth2RequestFactory() {
        return new DefaultOAuth2RequestFactory(clientDetailsService);
    }

    private boolean isAuthCodeRequest(Map<String, String> parameters) {
        return "authorization_code".equals(parameters.get("grant_type")) && parameters.get("code") != null;
    }

    private boolean isRefreshTokenRequest(Map<String, String> parameters) {
        return "refresh_token".equals(parameters.get("grant_type")) && parameters.get("refresh_token") != null;
    }

    private boolean saveToken(String jti, String content, long ttl) {
        String key = RedisKeys.LOGIN_TOKEN_PREFIX + jti;
        stringRedisTemplate.boundValueOps(key).set(content, ttl, TimeUnit.SECONDS);
        Long expire = stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
        return expire > 0;
    }
}
