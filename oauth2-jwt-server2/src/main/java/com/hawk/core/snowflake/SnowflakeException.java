package com.hawk.core.snowflake;

/**
 * 基于Snowflake算法的全局ID生成器异常类
 * 
 * @author HyouSyouSeki
 * @version 1.0.0 2018-03-21 16:41:31 初始创建
 */
public class SnowflakeException extends RuntimeException {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4853521468883834204L;
    
    public SnowflakeException(String message) {
        super(message);
    }
    
}
