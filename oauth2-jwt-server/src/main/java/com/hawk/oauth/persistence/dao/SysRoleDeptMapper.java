package com.hawk.oauth.persistence.dao;




import com.hawk.oauth.persistence.entity.SysRoleDept;

import java.util.List;

/**
 * @Title: SysRoleDeptMapper
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/27 15:39
 */
public interface SysRoleDeptMapper {

    int insert(SysRoleDept sysRoleDept);

    int insertBatch(List<SysRoleDept> list);

    int insertSelective(SysRoleDept sysRoleDept);

    int deleteByPrimaryKey(Long id);

    int deleteBatchByPrimaryKeys(List<Long> list);

    void updateByPrimaryKeySelective(SysRoleDept sysRoleDept);

    SysRoleDept selectByPrimaryKey(Long id);

    List<SysRoleDept> selectByConditions(SysRoleDept sysRoleDept);
}
