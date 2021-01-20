package com.hawk.oauth.exception;

/**
 * @Title: GlobalException
 * @ProjectName spring-cloud-oauth2-demo
 * @Author Hawk
 * @Date 2021/1/12 15:55
 */
public class GlobalException extends RuntimeException{
    /**
     * 异常
     */
    private Throwable cause;

    /**
     * 错误代码
     */
    private int code;

    /**
     * 异常消息
     */
    private String msg;

    /**
     * 默认构造方法
     */
    public GlobalException() {
        super();
    }



    /**
     * @param code 错误代码
     * @param msg
     */
    public GlobalException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * @param code 错误代码
     * @param msg   异常消息
     * @param cause
     */
    public GlobalException(int code, String msg, Throwable cause) {
        this.code = code;
        this.msg = msg;
        this.cause = cause;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setmsg(String msg) {
        this.msg = msg;
    }
}
