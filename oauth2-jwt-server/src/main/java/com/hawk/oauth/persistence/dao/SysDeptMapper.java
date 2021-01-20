package com.hawk.oauth.persistence.dao;




import com.hawk.oauth.persistence.entity.SysDept;

import java.util.List;

/**
 * @Title: SysDeptMapper
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/27 10:55
 */
public interface SysDeptMapper {

    int insert(SysDept sysDept);

    int insertBatch(List<SysDept> list);

    int insertSelective(SysDept sysDept);

    int deleteByPrimaryKey(Long id);

    int deleteBatchByPrimaryKeys(List<Long> id);

    void updateByPrimaryKeySelective(SysDept sysDept);

    SysDept selectByPrimaryKey(Long id);

    List<SysDept> selectByConditions(SysDept sysDept);

    List<SysDept> findAllRecursion();

    List<SysDept> findDeptRecursion(Long id);

}
