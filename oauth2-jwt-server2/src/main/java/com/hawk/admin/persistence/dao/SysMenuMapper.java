package com.hawk.admin.persistence.dao;

import com.hawk.admin.persistence.entity.SysMenu;

import java.util.List;

/**
 * @Title: SysMenuMapper
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/27 14:55
 */
public interface SysMenuMapper {

    int insert(SysMenu sysMenu);

    void insertBatch(List<SysMenu> list);

    void insertSelective(SysMenu sysMenu);

    void deleteByPrimaryKey(Long id);

    void deleteBatchByPrimaryKeys(List<Long> ids);

    void updateByPrimaryKeySelective(SysMenu sysMenu);

    SysMenu selectByPrimaryKey(Long id);

    List<SysMenu> selectByConditions(SysMenu sysMenu);

    List<SysMenu> selectByUserId(Long userId);

    List<SysMenu> selectByRoleId(Long roleId);

    List<SysMenu> findMenuRecursion(Long parentId);
}
