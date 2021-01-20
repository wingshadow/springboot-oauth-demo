package com.hawk.oauth.persistence.service;

import com.github.pagehelper.PageInfo;
import com.hawk.oauth.persistence.entity.SysUserRole;

import java.util.List;

/**
 * @Title: SysUserRoleService
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/28 10:33
 */
public interface SysUserRoleService {
    /**
     * 新增一条SysUserRole记录
     *
     * @param paramBean 要新增的SysUserRole记录数据对象
     * @return 新增记录的ID
     */
    Long insert(SysUserRole paramBean);

    /**
     * 批量新增SysUserRole记录
     *
     * @param paramBeans 要新增的SysUserRole记录数据对象集合
     */
    void insertBatch(List<SysUserRole> paramBeans);

    /**
     * 根据主键ID，删除一条SysUserRole记录
     *
     * @param id SysUserRole的主键
     */
    void deleteByPrimaryKey(Long id);

    /**
     * 根据主键ID，批量删除多条SysUserRole记录
     *
     * @param ids SysUserRole的主键字符串，多个主键用英文逗号分隔
     */
    void deleteBatchByPrimaryKeys(String ids);

    /**
     * 根据主键ID，批量删除多条SysUserRole记录
     *
     * @param ids SysUserRole的主键集合
     */
    void deleteBatchByPrimaryKeys(List<Long> ids);

    /**
     * 根据主键更新SysUserRole数据记录
     *
     * @param paramBean 要更新的SysUserRole数据对象
     */
    void updateByPrimaryKeySelective(SysUserRole paramBean);

    /**
     * 根据主键查询SysUserRole数据对象
     *
     * @param id SysUserRole的主键
     * @return SysUserRole数据对象
     */
    SysUserRole getByPrimaryKey(Long id);

    /**
     * 查询符合条件的SysUserRole结果集,根据paramBean动态拼接查询条件。
     *
     * @param paramBean 用于封装查询条件
     * @return SysUserRole数据查询结果集
     */
    List<SysUserRole> listByConditions(SysUserRole paramBean);

    /**
     * 分页查询符合条件的SysUserRole结果集
     *
     * @param paramBean 查询条件
     * @param pageNum 查询的页码
     * @param pageSize 每页记录数
     * @return 数据查询结果集
     */
    PageInfo<SysUserRole> listByPage(SysUserRole paramBean, final int pageNum
            , final int pageSize);

    void deleteByUserId(Long userId);

    void deleteByUserIds(List<Long> userIds);
}
