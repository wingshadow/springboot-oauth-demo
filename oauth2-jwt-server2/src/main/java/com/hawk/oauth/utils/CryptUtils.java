package com.hawk.oauth.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @Title: CryptUtils
 * @ProjectName springboot-oauth2-jwt
 * @Author Hawk
 * @Date 2021/1/19 12:56
 */
public class CryptUtils {

    public static String[] decode(String authorization) throws UnsupportedEncodingException {
        String ciphertext = authorization.split(" ")[1];

        byte[] bytes = Base64.getDecoder().decode(ciphertext);
        String text = new String(bytes, StandardCharsets.UTF_8);
        return StrUtil.split(text,":");
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String t = "WGNXZWJBcHA6WGNXZWJBcHA=";
        System.out.println(JSON.toJSONString(CryptUtils.decode(t)));
    }
}
