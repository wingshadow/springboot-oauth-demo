package com.hawk.oauth.bean;

public interface ResultCode {
    boolean success();
    int code();
    String message();
}
