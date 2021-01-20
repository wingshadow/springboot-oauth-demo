package com.hawk.oauth.persistence.service;

import com.github.pagehelper.PageInfo;
import com.hawk.oauth.persistence.entity.SysRole;

import java.util.List;

/**
 * @Title: SysRoleService
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/28 10:32
 */
public interface SysRoleService {

    /**
     * 新增一条SysRole记录
     *
     * @param paramBean 要新增的SysRole记录数据对象
     * @return 新增记录的ID
     */
    Long insert(SysRole paramBean);

    /**
     * 批量新增SysRole记录
     *
     * @param paramBeans 要新增的SysRole记录数据对象集合
     */
    void insertBatch(List<SysRole> paramBeans);

    /**
     * 根据主键ID，删除一条SysRole记录
     *
     * @param id SysRole的主键
     */
    void deleteByPrimaryKey(Long id);

    /**
     * 根据主键ID，批量删除多条SysRole记录
     *
     * @param ids SysRole的主键字符串，多个主键用英文逗号分隔
     */
    void deleteBatchByPrimaryKeys(String ids);

    /**
     * 根据主键ID，批量删除多条SysRole记录
     *
     * @param ids SysRole的主键集合
     */
    void deleteBatchByPrimaryKeys(List<Long> ids);

    /**
     * 根据主键更新SysRole数据记录
     *
     * @param paramBean 要更新的SysRole数据对象
     */
    void updateByPrimaryKeySelective(SysRole paramBean);

    /**
     * 根据主键查询SysRole数据对象
     *
     * @param id SysRole的主键
     * @return SysRole数据对象
     */
    SysRole getByPrimaryKey(Long id);

    /**
     * 查询符合条件的SysRole结果集,根据paramBean动态拼接查询条件。
     *
     * @param paramBean 用于封装查询条件
     * @return SysRole数据查询结果集
     */
    List<SysRole> listByConditions(SysRole paramBean);

    /**
     * 分页查询符合条件的SysRole结果集
     *
     * @param paramBean 查询条件
     * @param pageNum 查询的页码
     * @param pageSize 每页记录数
     * @return 数据查询结果集
     */
    PageInfo<SysRole> listByPage(SysRole paramBean, final int pageNum,
                                 final int pageSize);

    void deleteRoles(List<Long> roleIds);
}
