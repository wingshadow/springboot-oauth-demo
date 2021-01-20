package com.hawk.admin.persistence.service;

import com.github.pagehelper.PageInfo;
import com.hawk.admin.persistence.entity.SysRoleDept;

import java.util.List;

/**
 * @Title: SysRoleDeptService
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/28 10:31
 */
public interface SysRoleDeptService {
    /**
     * 新增一条SysRoleDept记录
     *
     * @param paramBean 要新增的SysRoleDept记录数据对象
     * @return 新增记录的ID
     */
    Long insert(SysRoleDept paramBean);

    /**
     * 批量新增SysRoleDept记录
     *
     * @param paramBeans 要新增的SysRoleDept记录数据对象集合
     */
    void insertBatch(List<SysRoleDept> paramBeans);

    /**
     * 根据主键ID，删除一条SysRoleDept记录
     *
     * @param id SysRoleDept的主键
     */
    void deleteByPrimaryKey(Long id);

    /**
     * 根据主键ID，批量删除多条SysRoleDept记录
     *
     * @param ids SysRoleDept的主键字符串，多个主键用英文逗号分隔
     */
    void deleteBatchByPrimaryKeys(String ids);

    /**
     * 根据主键ID，批量删除多条SysRoleDept记录
     *
     * @param ids SysRoleDept的主键集合
     */
    void deleteBatchByPrimaryKeys(List<Long> ids);

    /**
     * 根据主键更新SysRoleDept数据记录
     *
     * @param paramBean 要更新的SysRoleDept数据对象
     */
    void updateByPrimaryKeySelective(SysRoleDept paramBean);

    /**
     * 根据主键查询SysRoleDept数据对象
     *
     * @param id SysRoleDept的主键
     * @return SysRoleDept数据对象
     */
    SysRoleDept getByPrimaryKey(Long id);

    /**
     * 查询符合条件的SysRoleDept结果集,根据paramBean动态拼接查询条件。
     *
     * @param paramBean 用于封装查询条件
     * @return SysRoleDept数据查询结果集
     */
    List<SysRoleDept> listByConditions(SysRoleDept paramBean);

    /**
     * 分页查询符合条件的SysRoleDept结果集
     *
     * @param paramBean 查询条件
     * @param pageNum 查询的页码
     * @param pageSize 每页记录数
     * @return 数据查询结果集
     */
    PageInfo<SysRoleDept> listByPage(SysRoleDept paramBean, final int pageNum
            , final int pageSize);
}
