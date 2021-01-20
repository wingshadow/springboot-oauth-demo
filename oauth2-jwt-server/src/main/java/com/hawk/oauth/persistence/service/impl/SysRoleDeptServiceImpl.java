package com.hawk.oauth.persistence.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.google.common.base.Splitter;
import com.hawk.oauth.core.base.AbstractBaseServiceImpl;
import com.hawk.oauth.persistence.dao.SysRoleDeptMapper;
import com.hawk.oauth.persistence.entity.SysRoleDept;
import com.hawk.oauth.persistence.service.SysRoleDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: SysRoleDeptServiceImpl
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/28 11:00
 */
@Service
public class SysRoleDeptServiceImpl extends AbstractBaseServiceImpl implements SysRoleDeptService {

    @Autowired
    private SysRoleDeptMapper sysRoleDeptMapper;


    @Override
    public Long insert(SysRoleDept paramBean) {
        Long newId = getNextId();
        paramBean.setId(newId);
        sysRoleDeptMapper.insert(paramBean);
        return newId;
    }

    @Override
    public void insertBatch(List<SysRoleDept> paramBeans) {
        for (SysRoleDept item : paramBeans) {
            Long newId = getNextId();
            item.setId(newId);
        }
        sysRoleDeptMapper.insertBatch(paramBeans);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        sysRoleDeptMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatchByPrimaryKeys(String ids) {
        List<String> stringList = Splitter.on(",").splitToList(ids);
        List<Long> idLst = stringList.stream().map(c-> Long.parseLong(c)).collect(Collectors.toList());
        deleteBatchByPrimaryKeys(idLst);
    }

    @Override
    public void deleteBatchByPrimaryKeys(List<Long> ids) {
        sysRoleDeptMapper.deleteBatchByPrimaryKeys(ids);
    }

    @Override
    public void updateByPrimaryKeySelective(SysRoleDept paramBean) {
        sysRoleDeptMapper.updateByPrimaryKeySelective(paramBean);
    }

    @Override
    public SysRoleDept getByPrimaryKey(Long id) {
        return sysRoleDeptMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysRoleDept> listByConditions(SysRoleDept paramBean) {
        return sysRoleDeptMapper.selectByConditions(paramBean);
    }

    @Override
    public PageInfo<SysRoleDept> listByPage(SysRoleDept paramBean, final int pageNum, final int pageSize) {
        PageMethod.startPage(pageNum, pageSize);
        List<SysRoleDept> lst = sysRoleDeptMapper.selectByConditions(paramBean);
        return new PageInfo<>(lst);
    }
}
