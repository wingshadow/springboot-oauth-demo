package com.hawk.core.base;

import com.hawk.core.snowflake.SnowflakeSeq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Title: AbstractBaseServiceImpl
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/26 21:05
 */
@Slf4j
@Component
public class AbstractBaseServiceImpl {
    @Resource(name = "snowflakeSeq")
    private SnowflakeSeq snowflakeSeq;

    public long getNextId() {
        return snowflakeSeq.nextId();
    }
}
