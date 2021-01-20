package com.hawk.oauth.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: RefreshRequest
 * @ProjectName spring-cloud-oauth2-demo
 * @Author Hawk
 * @Date 2021/1/12 9:35
 * <pre>
 *     刷新token
 * </pre>
 */
@Data
public class RefreshRequest implements Serializable {
    String refreshToken;
}
