package com.hawk.oauth.persistence.service;

import com.github.pagehelper.PageInfo;
import com.hawk.oauth.persistence.entity.SysMenu;
import com.hawk.oauth.persistence.entity.SysUser;

import java.util.List;

/**
 * @Title: SysMenuService
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/27 14:53
 */
public interface SysMenuService {
    /**
     * 新增一条SysMenu记录
     *
     * @param paramBean 要新增的SysMenu记录数据对象
     * @return 新增记录的ID
     */
    Long insert(SysMenu paramBean);

    /**
     * 批量新增SysMenu记录
     *
     * @param paramBeans 要新增的SysMenu记录数据对象集合
     */
    void insertBatch(List<SysMenu> paramBeans);

    /**
     * 根据主键ID，删除一条SysMenu记录
     *
     * @param id SysMenu的主键
     */
    void deleteByPrimaryKey(Long id);

    /**
     * 根据主键ID，批量删除多条SysMenu记录
     *
     * @param ids SysMenu的主键字符串，多个主键用英文逗号分隔
     */
    void deleteBatchByPrimaryKeys(String ids);

    /**
     * 根据主键ID，批量删除多条SysMenu记录
     *
     * @param ids SysMenu的主键集合
     */
    void deleteBatchByPrimaryKeys(List<Long> ids);

    /**
     * 根据主键更新SysMenu数据记录
     *
     * @param paramBean 要更新的SysMenu数据对象
     */
    void updateByPrimaryKeySelective(SysMenu paramBean);

    /**
     * 根据主键查询SysMenu数据对象
     *
     * @param id SysMenu的主键
     * @return SysMenu数据对象
     */
    SysMenu getByPrimaryKey(Long id);

    /**
     * 查询符合条件的SysMenu结果集,根据paramBean动态拼接查询条件。
     *
     * @param paramBean 用于封装查询条件
     * @return SysMenu数据查询结果集
     */
    List<SysMenu> listByConditions(SysMenu paramBean);

    /**
     * 分页查询符合条件的SysMenu结果集
     *
     * @param paramBean 查询条件
     * @param pageNum 查询的页码
     * @param pageSize 每页记录数
     * @return 数据查询结果集
     */
    PageInfo<SysMenu> listByPage(SysMenu paramBean, final int pageNum,
                                 final int pageSize);

    /**
     * 查询用户的菜单信息
     * @param user
     * @return
     */
    List<SysMenu> listByUserId(SysUser user);

    /**
     * 查询角色菜单信息
     * @param roleId
     * @return
     */
    List<SysMenu> listByRoleId(Long roleId);

    /**
     * 查询菜单信息返回Tree结构List
     * @return
     */
    List<SysMenu> findMenuRecursion(Long parentId);

    List<SysMenu> findUserMenuTree(SysUser user);
}
