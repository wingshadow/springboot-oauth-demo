package com.hawk.oauth.persistence.dao;




import com.hawk.oauth.persistence.entity.SysRoleMenu;

import java.util.List;

/**
 * @Title: SysRoleMenuMapper
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/27 15:41
 */
public interface SysRoleMenuMapper {

    int insert(SysRoleMenu sysRoleMenu);

    int insertBatch(List<SysRoleMenu> list);

    int insertSelective(SysRoleMenu sysRoleMenu);

    int deleteByPrimaryKey(Long id);

    int deleteBatchByPrimaryKeys(List<Long> list);

    void updateByPrimaryKeySelective(SysRoleMenu sysRoleMenu);

    SysRoleMenu selectByPrimaryKey(Long id);

    List<SysRoleMenu> selectByConditions(SysRoleMenu sysRoleMenu);

    void deleteByRoleId(Long id);

    void deleteBatchByRoleIds(List<Long> list);
}
