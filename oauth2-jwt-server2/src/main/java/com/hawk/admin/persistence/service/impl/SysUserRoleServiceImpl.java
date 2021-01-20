package com.hawk.admin.persistence.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.google.common.base.Splitter;
import com.hawk.core.base.AbstractBaseServiceImpl;
import com.hawk.admin.persistence.dao.SysUserRoleMapper;
import com.hawk.admin.persistence.entity.SysUserRole;
import com.hawk.admin.persistence.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: SysUserRoleServiceImpl
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/28 11:22
 */
@Service
public class SysUserRoleServiceImpl extends AbstractBaseServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Long insert(SysUserRole paramBean) {
        Long newId = getNextId();
        paramBean.setId(newId);
        sysUserRoleMapper.insert(paramBean);
        return newId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBatch(List<SysUserRole> paramBeans) {
        if(CollUtil.isNotEmpty(paramBeans)){
            SysUserRole sysUserRole = paramBeans.get(0);
            deleteByUserId(sysUserRole.getUserId());
            for (SysUserRole item : paramBeans) {
                Long newId = getNextId();
                item.setId(newId);
            }

            sysUserRoleMapper.insertBatch(paramBeans);
        }

    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        sysUserRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatchByPrimaryKeys(String ids) {
        List<String> stringList = Splitter.on(",").splitToList(ids);
        List<Long> idLst = stringList.stream().map(c-> Long.parseLong(c)).collect(Collectors.toList());
        deleteBatchByPrimaryKeys(idLst);
    }

    @Override
    public void deleteBatchByPrimaryKeys(List<Long> ids) {
        sysUserRoleMapper.deleteBatchByPrimaryKeys(ids);
    }

    @Override
    public void updateByPrimaryKeySelective(SysUserRole paramBean) {
        sysUserRoleMapper.updateByPrimaryKeySelective(paramBean);
    }

    @Override
    public SysUserRole getByPrimaryKey(Long id) {
        return sysUserRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysUserRole> listByConditions(SysUserRole paramBean) {
        return sysUserRoleMapper.selectByConditions(paramBean);
    }

    @Override
    public PageInfo<SysUserRole> listByPage(SysUserRole paramBean, final int pageNum, final int pageSize) {
        PageMethod.startPage(pageNum, pageSize);
        List<SysUserRole> lst = sysUserRoleMapper.selectByConditions(paramBean);
        return new PageInfo<>(lst);
    }

    @Override
    public void deleteByUserId(Long userId) {
        sysUserRoleMapper.deleteByUserId(userId);
    }

    @Override
    public void deleteByUserIds(List<Long> userIds){
        sysUserRoleMapper.deleteByUserIds(userIds);
    }
}
