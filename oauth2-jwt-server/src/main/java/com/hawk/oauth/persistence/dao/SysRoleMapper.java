package com.hawk.oauth.persistence.dao;




import com.hawk.oauth.persistence.entity.SysRole;

import java.util.List;

/**
 * @Title: SysRoleMapper
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/27 15:33
 */
public interface SysRoleMapper {

    int insert(SysRole sysRole);

    int insertBatch(List<SysRole> list);

    int insertSelective(SysRole sysRole);

    int deleteByPrimaryKey(Long id);

    int deleteBatchByPrimaryKeys(List<Long> list);

    void updateByPrimaryKeySelective(SysRole sysRole);

    SysRole selectByPrimaryKey(Long id);

    List<SysRole> selectByConditions(SysRole sysRole);
}
