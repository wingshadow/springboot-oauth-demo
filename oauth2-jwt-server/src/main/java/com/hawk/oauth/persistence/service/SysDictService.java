package com.hawk.oauth.persistence.service;

import com.github.pagehelper.PageInfo;
import com.hawk.oauth.persistence.entity.SysDict;

import java.util.List;

/**
 * SysDictService Service接口定义类
 * 
 * @author fy
 * @version 1.0.0 2019-10-22 08:45:49 初始创建
 */
public interface SysDictService {
    
    /**
     * 新增一条SysDict记录
     * 
     * @param paramBean 要新增的SysDict记录数据对象
     * @return 新增记录的ID
     */
    Long insert(SysDict paramBean);
    
    /**
     * 批量新增SysDict记录
     * 
     * @param paramBeans 要新增的SysDict记录数据对象集合
     */
    void insertBatch(List<SysDict> paramBeans);
    
    /**
     * 根据主键ID，删除一条SysDict记录
     * 
     * @param id SysDict的主键
     */
    void deleteByPrimaryKey(Long id);
    
    /**
     * 根据主键ID，批量删除多条SysDict记录
     *
     * @param ids SysDict的主键字符串，多个主键用英文逗号分隔
     */
    void deleteBatchByPrimaryKeys(String ids);
    
    /**
     * 根据主键ID，批量删除多条SysDict记录
     *
     * @param ids SysDict的主键集合
     */
    void deleteBatchByPrimaryKeys(List<Long> ids);
    
    /**
     * 根据主键更新SysDict数据记录
     * 
     * @param paramBean 要更新的SysDict数据对象
     */
    void updateByPrimaryKeySelective(SysDict paramBean);
    
    /**
     * 根据主键查询SysDict数据对象
     * 
     * @param id SysDict的主键
     * @return SysDict数据对象
     */
    SysDict getByPrimaryKey(Long id);
    
    /**
     * 查询符合条件的SysDict结果集,根据paramBean动态拼接查询条件。
     * 
     * @param paramBean 用于封装查询条件
     * @return SysDict数据查询结果集
     */
    List<SysDict> listByConditions(SysDict paramBean);
    
    /**
     * 分页查询符合条件的SysDict结果集
     * 
     * @param paramBean 查询条件
     * @param pageNum 查询的页码
     * @param pageSize 每页记录数
     * @return 数据查询结果集
     */
    PageInfo<SysDict> listByPage(SysDict paramBean, final int pageNum,
                                 final int pageSize);
    
}
