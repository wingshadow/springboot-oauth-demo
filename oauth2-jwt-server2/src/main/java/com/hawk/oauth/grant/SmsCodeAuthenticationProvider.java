package com.hawk.oauth.grant;

import com.hawk.common.constraint.RedisKeys;
import com.hawk.oauth.exception.InvalidSmsCodeException;
import com.hawk.oauth.service.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Title: SmsCodeAuthenticationProvider
 * @ProjectName spring-cloud-oauth2-demo
 * @Author Hawk
 * @Date 2021/1/11 10:07
 */
@Slf4j
public class SmsCodeAuthenticationProvider implements AuthenticationProvider, MessageSourceAware {

    private DaoAuthenticationProvider daoAuthenticationProvider;

    private StringRedisTemplate stringRedisTemplate;

    private UserDetailsServiceImpl userDetailsService;

    private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    /**
     * 是否隐藏用户未发现异常，默认为true,为true返回的异常信息为BadCredentialsException
     */
    private boolean hideUserNotFoundExceptions = true;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String mobile = (String) authentication.getPrincipal();
        if (mobile == null) {
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Missing mobile"));
        }
        String code = (String) authentication.getCredentials();
        if (code == null) {
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Missing code"));
        }
        // 从redis中获取验证码
        String cacheCode = stringRedisTemplate.opsForValue().get(RedisKeys.LOGIN_SMS_PREFIX + mobile);
        if (StringUtils.isBlank(cacheCode) || !cacheCode.equals(code)) {
            log.error("短信验证码错误");
            throw new InvalidSmsCodeException(this.messages.getMessage("", "无效验证码"));
        }
        UserDetails user = userDetailsService.loadUserByMobile(mobile);
        if(user == null){
            throw new InternalAuthenticationServiceException(this.messages.getMessage("", "用户不存在"));
        }
        check(user);
        SmsCodeAuthenticationToken authenticationToken = new SmsCodeAuthenticationToken(user, code,
                user.getAuthorities());
        authenticationToken.setDetails(authenticationToken.getDetails());
        return authenticationToken;
    }

    private void removeCode(String mobile) {
        stringRedisTemplate.delete(RedisKeys.LOGIN_SMS_PREFIX + mobile);
    }

    /**
     * 判断验证方式是符合当前认证方法类型
     * 指定该认证提供者验证Token对象
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        // 判断 authentication 是不是 MobileCodeAuthenticationToken 的子类或子接口
        return SmsCodeAuthenticationToken.class.isAssignableFrom(aClass);
    }

    /**
     * 账号禁用、锁定、超时校验
     *
     * @param user
     */
    private void check(UserDetails user) {
        if (!user.isAccountNonLocked()) {
            throw new LockedException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.locked", "User account is locked"));
        } else if (!user.isEnabled()) {
            throw new DisabledException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.disabled", "User is disabled"));
        } else if (!user.isAccountNonExpired()) {
            throw new AccountExpiredException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.expired", "User account has expired"));
        }
    }

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void setHideUserNotFoundExceptions(boolean hideUserNotFoundExceptions) {
        this.hideUserNotFoundExceptions = hideUserNotFoundExceptions;
    }

    public void setUserDetailsService(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
