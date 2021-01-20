package com.hawk.admin.persistence.service;

import com.github.pagehelper.PageInfo;
import com.hawk.admin.persistence.entity.SysRoleMenu;

import java.util.List;

/**
 * @Title: SysRoleMenuService
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/28 10:32
 */
public interface SysRoleMenuService {

    /**
     * 新增一条SysRoleMenu记录
     *
     * @param paramBean 要新增的SysRoleMenu记录数据对象
     * @return 新增记录的ID
     */
    Long insert(SysRoleMenu paramBean);

    /**
     * 批量新增SysRoleMenu记录
     *
     * @param paramBeans 要新增的SysRoleMenu记录数据对象集合
     */
    void insertBatch(List<SysRoleMenu> paramBeans);

    /**
     * 根据主键ID，删除一条SysRoleMenu记录
     *
     * @param id SysRoleMenu的主键
     */
    void deleteByPrimaryKey(Long id);

    /**
     * 根据主键ID，批量删除多条SysRoleMenu记录
     *
     * @param ids SysRoleMenu的主键字符串，多个主键用英文逗号分隔
     */
    void deleteBatchByPrimaryKeys(String ids);

    /**
     * 根据主键ID，批量删除多条SysRoleMenu记录
     *
     * @param ids SysRoleMenu的主键集合
     */
    void deleteBatchByPrimaryKeys(List<Long> ids);

    /**
     * 根据主键更新SysRoleMenu数据记录
     *
     * @param paramBean 要更新的SysRoleMenu数据对象
     */
    void updateByPrimaryKeySelective(SysRoleMenu paramBean);

    /**
     * 根据主键查询SysRoleMenu数据对象
     *
     * @param id SysRoleMenu的主键
     * @return SysRoleMenu数据对象
     */
    SysRoleMenu getByPrimaryKey(Long id);

    /**
     * 查询符合条件的SysRoleMenu结果集,根据paramBean动态拼接查询条件。
     *
     * @param paramBean 用于封装查询条件
     * @return SysRoleMenu数据查询结果集
     */
    List<SysRoleMenu> listByConditions(SysRoleMenu paramBean);

    /**
     * 分页查询符合条件的SysRoleMenu结果集
     *
     * @param paramBean 查询条件
     * @param pageNum 查询的页码
     * @param pageSize 每页记录数
     * @return 数据查询结果集
     */
    PageInfo<SysRoleMenu> listByPage(SysRoleMenu paramBean, final int pageNum
            , final int pageSize);

    void deleteByRoleId(Long roleId);

    void deleteBatchByRoleIds(List<Long> ids);
}
