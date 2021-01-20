package com.hawk.oauth.persistence.dao;




import com.hawk.oauth.persistence.entity.SysUser;

import java.util.List;

/**
 * @Title: SysUserMapper
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/27 15:47
 */
public interface SysUserMapper {

    int insert(SysUser sysUser);

    int insertBatch(List<SysUser> list);

    int insertSelective(SysUser sysUser);

    int deleteByPrimaryKey(Long id);

    int deleteBatchByPrimaryKeys(List<Long> list);

    void updateByPrimaryKeySelective(SysUser sysUser);

    SysUser selectByPrimaryKey(Long id);

    List<SysUser> selectByConditions(SysUser sysUser);

    SysUser selectByName(String name);

    SysUser selectByMobile(String mobile);
}
