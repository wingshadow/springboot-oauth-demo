package com.hawk.oauth.persistence.service;

import com.github.pagehelper.PageInfo;
import com.hawk.oauth.persistence.entity.SysConfig;

import java.util.List;

/**
 * SysConfigService Service接口定义类
 * 
 * @author fy
 * @version 1.0.0 2019-10-22 08:48:13 初始创建
 */
public interface SysConfigService {
    
    /**
     * 新增一条SysConfig记录
     * 
     * @param paramBean 要新增的SysConfig记录数据对象
     * @return 新增记录的ID
     */
    Long insert(SysConfig paramBean);
    
    /**
     * 批量新增SysConfig记录
     * 
     * @param paramBeans 要新增的SysConfig记录数据对象集合
     */
    void insertBatch(List<SysConfig> paramBeans);
    
    /**
     * 根据主键ID，删除一条SysConfig记录
     * 
     * @param id SysConfig的主键
     */
    void deleteByPrimaryKey(Long id);
    
    /**
     * 根据主键ID，批量删除多条SysConfig记录
     *
     * @param ids SysConfig的主键字符串，多个主键用英文逗号分隔
     */
    void deleteBatchByPrimaryKeys(String ids);
    
    /**
     * 根据主键ID，批量删除多条SysConfig记录
     *
     * @param ids SysConfig的主键集合
     */
    void deleteBatchByPrimaryKeys(List<Long> ids);
    
    /**
     * 根据主键更新SysConfig数据记录
     * 
     * @param paramBean 要更新的SysConfig数据对象
     */
    void updateByPrimaryKeySelective(SysConfig paramBean);
    
    /**
     * 根据主键查询SysConfig数据对象
     * 
     * @param id SysConfig的主键
     * @return SysConfig数据对象
     */
    SysConfig getByPrimaryKey(Long id);
    
    /**
     * 查询符合条件的SysConfig结果集,根据paramBean动态拼接查询条件。
     * 
     * @param paramBean 用于封装查询条件
     * @return SysConfig数据查询结果集
     */
    List<SysConfig> listByConditions(SysConfig paramBean);
    
    /**
     * 分页查询符合条件的SysConfig结果集
     * 
     * @param paramBean 查询条件
     * @param pageNum 查询的页码
     * @param pageSize 每页记录数
     * @return 数据查询结果集
     */
    PageInfo<SysConfig> listByPage(SysConfig paramBean, final int pageNum,
                                   final int pageSize);
    
}
