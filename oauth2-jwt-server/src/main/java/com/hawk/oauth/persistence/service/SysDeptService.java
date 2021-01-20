package com.hawk.oauth.persistence.service;

import com.github.pagehelper.PageInfo;
import com.hawk.oauth.persistence.entity.SysDept;

import java.util.List;

/**
 * @Title: SysDeptService
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/27 14:35
 */
public interface SysDeptService {
    /**
     * 新增一条SysDept记录
     *
     * @param paramBean 要新增的SysDept记录数据对象
     * @return 新增记录的ID
     */
    Long insert(SysDept paramBean);

    /**
     * 批量新增SysDept记录
     *
     * @param paramBeans 要新增的SysDept记录数据对象集合
     */
    void insertBatch(List<SysDept> paramBeans);

    /**
     * 根据主键ID，删除一条SysDept记录
     *
     * @param id SysDept的主键
     */
    void deleteByPrimaryKey(Long id);

    /**
     * 根据主键ID，批量删除多条SysDept记录
     *
     * @param ids SysDept的主键字符串，多个主键用英文逗号分隔
     */
    void deleteBatchByPrimaryKeys(String ids);

    /**
     * 根据主键ID，批量删除多条SysDept记录
     *
     * @param ids SysDept的主键集合
     */
    void deleteBatchByPrimaryKeys(List<Long> ids);

    /**
     * 根据主键更新SysDept数据记录
     *
     * @param paramBean 要更新的SysDept数据对象
     */
    void updateByPrimaryKeySelective(SysDept paramBean);

    /**
     * 根据主键查询SysDept数据对象
     *
     * @param id SysDept的主键
     * @return SysDept数据对象
     */
    SysDept getByPrimaryKey(Long id);

    /**
     * 查询符合条件的SysDept结果集,根据paramBean动态拼接查询条件。
     *
     * @param paramBean 用于封装查询条件
     * @return SysDept数据查询结果集
     */
    List<SysDept> listByConditions(SysDept paramBean);

    /**
     * 分页查询符合条件的SysDept结果集
     *
     * @param paramBean 查询条件
     * @param pageNum 查询的页码
     * @param pageSize 每页记录数
     * @return 数据查询结果集
     */
    PageInfo<SysDept> listByPage(SysDept paramBean, final int pageNum,
                                 final int pageSize);

    /**
     * 查询所有部门及子部门的信息
     * @return
     */
    List<SysDept> findAllRecursion();

    /**
     * 查询下属部门及其子部门信息
     * @param parentId
     * @return
     */
    List<SysDept> findDeptRecursion(Long parentId);
}
