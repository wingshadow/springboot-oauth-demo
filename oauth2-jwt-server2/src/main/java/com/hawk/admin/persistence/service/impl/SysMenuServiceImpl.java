package com.hawk.admin.persistence.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.google.common.base.Splitter;
import com.hawk.core.base.AbstractBaseServiceImpl;
import com.hawk.admin.persistence.dao.SysMenuMapper;
import com.hawk.admin.persistence.entity.SysMenu;
import com.hawk.admin.persistence.entity.SysUser;
import com.hawk.admin.persistence.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Title: SysMenuServiceImp
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/27 15:13
 */
@Service
public class SysMenuServiceImpl extends AbstractBaseServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public Long insert(SysMenu paramBean) {
        Long newId = getNextId();
        paramBean.setId(newId);
        sysMenuMapper.insert(paramBean);
        return newId;
    }

    @Override
    public void insertBatch(List<SysMenu> paramBeans) {
        for (SysMenu item : paramBeans) {
            Long newId = getNextId();
            item.setId(newId);
        }

        sysMenuMapper.insertBatch(paramBeans);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        sysMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatchByPrimaryKeys(String ids) {
        List<String> stringList = Splitter.on(",").splitToList(ids);
        List<Long> idLst = stringList.stream().map(c-> Long.parseLong(c)).collect(Collectors.toList());
        deleteBatchByPrimaryKeys(idLst);
    }

    @Override
    public void deleteBatchByPrimaryKeys(List<Long> ids) {
        sysMenuMapper.deleteBatchByPrimaryKeys(ids);
    }

    @Override
    public void updateByPrimaryKeySelective(SysMenu paramBean) {
        sysMenuMapper.updateByPrimaryKeySelective(paramBean);
    }

    @Override
    public SysMenu getByPrimaryKey(Long id) {
        return sysMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysMenu> listByConditions(SysMenu paramBean) {
        return sysMenuMapper.selectByConditions(paramBean);
    }

    @Override
    public PageInfo<SysMenu> listByPage(SysMenu paramBean, final int pageNum,
                                        final int pageSize) {
        PageMethod.startPage(pageNum, pageSize);
        List<SysMenu> lst = sysMenuMapper.selectByConditions(paramBean);
        return new PageInfo<>(lst);
    }

    @Override
    public List<SysMenu> listByUserId(SysUser user) {
        List<SysMenu> list;
        if(user.getName().equals("admin")){
            list = listByConditions(null);
        }else {
            list = sysMenuMapper.selectByUserId(user.getId());
        }
        return list;
    }


    @Override
    public List<SysMenu> listByRoleId(Long roleId) {
        List<SysMenu> lst = sysMenuMapper.selectByRoleId(roleId);
        return lst;
    }

    @Override
    public List<SysMenu> findMenuRecursion(Long parentId) {
        List<SysMenu> lst = sysMenuMapper.findMenuRecursion(parentId);
        return lst;
    }

    @Override
    public List<SysMenu> findUserMenuTree(SysUser user) {
        List<SysMenu> list;
        if(user.getName().equals("admin")){
            list = listByConditions(null);
        }else{
            list = listByUserId(user);
        }

        if (CollUtil.isEmpty(list)) {
            return new ArrayList<>();
        }
        Set<Long> menuIdSet = new HashSet<Long>();
        for (SysMenu sysMenu : list) {
            menuIdSet.add(sysMenu.getId());
        }

        List<SysMenu> listTree = findMenuRecursion(0L);
        recursion(listTree, menuIdSet);
        return listTree;
    }

    /**
     * 递归方式删除不属于用户的菜单项及用户按钮选项
     *
     * @param treeList
     * @param hashSet
     */
    private void recursion(List<SysMenu> treeList, Set<Long> hashSet) {
        if (CollUtil.isNotEmpty(treeList)) {
            List<SysMenu> rmvList = new ArrayList<>(treeList.size());
            for (SysMenu menuDO : treeList) {
                if (hashSet.contains(menuDO.getId())) {
                    if (menuDO.getType() == 2) {
                        rmvList.add(menuDO);
                    } else {
                        recursion(menuDO.getChildren(), hashSet);
                    }

                } else {
                    rmvList.add(menuDO);
                }
            }
            treeList.removeAll(rmvList);
        }
    }
}
