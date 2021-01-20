package com.hawk.admin.persistence.dao;

import com.hawk.admin.persistence.entity.SysUserRole;

import java.util.List;

/**
 * @Title: SysUserRoleMapper
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/27 15:48
 */
public interface SysUserRoleMapper {

    int insert(SysUserRole sysUserRole);

    int insertBatch(List<SysUserRole> list);

    int insertSelective(SysUserRole sysUserRole);

    int deleteByPrimaryKey(Long id);

    int deleteBatchByPrimaryKeys(List<Long> list);

    void updateByPrimaryKeySelective(SysUserRole sysUserRole);

    SysUserRole selectByPrimaryKey(Long id);

    List<SysUserRole> selectByConditions(SysUserRole sysUserRole);

    void deleteByUserId(Long userId);

    void deleteByUserIds(List<Long> list);
}
