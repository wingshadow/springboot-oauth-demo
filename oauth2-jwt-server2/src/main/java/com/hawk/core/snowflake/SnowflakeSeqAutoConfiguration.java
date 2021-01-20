package com.hawk.core.snowflake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 分布式ID生成器
 * 
 * @author HyouSyouSeki
 * @version 1.0.0 2018-03-21 17:11:17 初始创建
 * @version 1.0.0 2018-08-20 09:25:53 修改SnowflakeSeq构造方法
 */
@Configuration
@ConditionalOnProperty(prefix = "owl.seq", name = "snowflake", matchIfMissing = true)
public class SnowflakeSeqAutoConfiguration {
    
    private static final Logger log = LoggerFactory.getLogger(SnowflakeSeqAutoConfiguration.class);
    
    /**
     * @return 分布式ID生成器参数配置
     */
    @Bean
    @ConfigurationProperties(prefix = "owl.seq.snowflake")
    public SnowflakeProp snowflakeProp() {
        return new SnowflakeProp();
    }
    
    @Bean
    @ConditionalOnMissingBean(name = "snowflakeSeq")
    public SnowflakeSeq snowflakeSeq(@Qualifier("snowflakeProp") SnowflakeProp snowflakeProp) {
        log.info("snowflakeProp : {}", snowflakeProp);
        return new SnowflakeSeq(snowflakeProp);
    }

    @Bean
    @ConditionalOnMissingBean(name = "snowflakeIdVariant")
    public SnowflakeIdVariant snowflakeIdVariant(@Qualifier("snowflakeProp") SnowflakeProp snowflakeProp){
        log.info("snowflakeProp : {}", snowflakeProp);
        return new SnowflakeIdVariant(snowflakeProp);
    }
    
}
