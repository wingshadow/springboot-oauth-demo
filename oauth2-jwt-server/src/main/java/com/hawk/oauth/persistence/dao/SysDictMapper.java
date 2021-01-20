package com.hawk.oauth.persistence.dao;




import com.hawk.oauth.persistence.entity.SysDict;

import java.util.List;

/**
 * @Title: SysDictMapper
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/26 20:55
 */
public interface SysDictMapper {

    int insert(SysDict sysDict);

    int insertBatch(List<SysDict> list);

    int insertSelective(SysDict sysDict);

    int deleteByPrimaryKey(Long id);

    void deleteBatchByPrimaryKeys(List<Long> list);

    int updateByPrimaryKeySelective(SysDict sysDict);

    SysDict selectByPrimaryKey(Long id);

    List<SysDict> selectByConditions(SysDict sysDict);
}
