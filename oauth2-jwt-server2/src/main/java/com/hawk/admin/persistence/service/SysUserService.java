package com.hawk.admin.persistence.service;

import com.hawk.admin.persistence.entity.SysUser;
import com.hawk.admin.persistence.entity.SysUserRole;

import java.util.List;
import java.util.Set;

/**
 * @Title: SysUserService
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/28 10:33
 */
public interface SysUserService {

    /**
     * 新增一条SysUser记录
     *
     * @param paramBean 要新增的SysUser记录数据对象
     * @return 新增记录的ID
     */
    Long insert(SysUser paramBean);

    /**
     * 批量新增SysUser记录
     *
     * @param paramBeans 要新增的SysUser记录数据对象集合
     */
    void insertBatch(List<SysUser> paramBeans);

    /**
     * 根据主键ID，删除一条SysUser记录
     *
     * @param id SysUser的主键
     */
    void deleteByPrimaryKey(Long id);

    /**
     * 根据主键ID，批量删除多条SysUser记录
     *
     * @param ids SysUser的主键字符串，多个主键用英文逗号分隔
     */
    void deleteBatchByPrimaryKeys(String ids);

    /**
     * 根据主键ID，批量删除多条SysUser记录
     *
     * @param ids SysUser的主键集合
     */
    void deleteBatchByPrimaryKeys(List<Long> ids);

    /**
     * 根据主键更新SysUser数据记录
     *
     * @param paramBean 要更新的SysUser数据对象
     */
    void updateByPrimaryKeySelective(SysUser paramBean);

    /**
     * 根据主键查询SysUser数据对象
     *
     * @param id SysUser的主键
     * @return SysUser数据对象
     */
    SysUser getByPrimaryKey(Long id);

    /**
     * 查询符合条件的SysUser结果集,根据paramBean动态拼接查询条件。
     *
     * @param paramBean 用于封装查询条件
     * @return SysUser数据查询结果集
     */
    List<SysUser> listByConditions(SysUser paramBean);


    /**
     * 查询用户权限结果集
     *
     * @param userId
     * @return
     */
    Set<String> findPermissions(Long userId);

    Set<String> findALLPermissions();

    SysUser findUserName(String userName);

    void save(SysUser sysUser, List<SysUserRole> list);

    void update(SysUser sysUser, List<SysUserRole> list);

    void deleteUserIds(List<Long> userIds);

    SysUser selectByName(String name);

    SysUser selectByMobile(String mobile);


}
