package com.hawk.admin.persistence.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.google.common.base.Splitter;
import com.hawk.core.base.AbstractBaseServiceImpl;
import com.hawk.admin.persistence.dao.SysRoleMapper;
import com.hawk.admin.persistence.dao.SysRoleMenuMapper;
import com.hawk.admin.persistence.entity.SysRole;
import com.hawk.admin.persistence.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: SysRoleServiceImpl
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/28 11:10
 */
@Service
public class SysRoleServiceImpl extends AbstractBaseServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public Long insert(SysRole paramBean) {
        Long newId = getNextId();
        paramBean.setId(newId);
        sysRoleMapper.insert(paramBean);
        return newId;
    }

    @Override
    public void insertBatch(List<SysRole> paramBeans) {
        for (SysRole item : paramBeans) {
            Long newId = getNextId();
            item.setId(newId);
        }
        sysRoleMapper.insertBatch(paramBeans);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        sysRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatchByPrimaryKeys(String ids) {
        List<String> stringList = Splitter.on(",").splitToList(ids);
        List<Long> idLst = stringList.stream().map(c-> Long.parseLong(c)).collect(Collectors.toList());
        deleteBatchByPrimaryKeys(idLst);
    }

    @Override
    public void deleteBatchByPrimaryKeys(List<Long> ids) {
        sysRoleMapper.deleteBatchByPrimaryKeys(ids);
    }

    @Override
    public void updateByPrimaryKeySelective(SysRole paramBean) {
        sysRoleMapper.updateByPrimaryKeySelective(paramBean);
    }

    @Override
    public SysRole getByPrimaryKey(Long id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysRole> listByConditions(SysRole paramBean) {
        return sysRoleMapper.selectByConditions(paramBean);
    }

    @Override
    public PageInfo<SysRole> listByPage(SysRole paramBean, final int pageNum, final int pageSize) {
        PageMethod.startPage(pageNum, pageSize);
        List<SysRole> lst = sysRoleMapper.selectByConditions(paramBean);
        return new PageInfo<>(lst);
    }

    @Override
    public void deleteRoles(List<Long> roleIds){
        deleteBatchByPrimaryKeys(roleIds);
        sysRoleMenuMapper.deleteBatchByRoleIds(roleIds);
    }
}
