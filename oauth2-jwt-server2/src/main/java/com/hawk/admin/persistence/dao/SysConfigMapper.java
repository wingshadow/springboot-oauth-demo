package com.hawk.admin.persistence.dao;


import com.hawk.admin.persistence.entity.SysConfig;

import java.util.List;

/**
 * @Title: SysConfigMapper
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/27 10:47
 */
public interface SysConfigMapper {

    int insert(SysConfig sysConfig);

    int insertBatch(List<SysConfig> list);

    int insertSelective(SysConfig sysConfig);

    int deleteByPrimaryKey(Long id);

    int deleteBatchByPrimaryKeys(List<Long> list);

    void updateByPrimaryKeySelective(SysConfig sysConfig);

    SysConfig selectByPrimaryKey(Long id);

    List<SysConfig> selectByConditions(SysConfig sysConfig);
}
