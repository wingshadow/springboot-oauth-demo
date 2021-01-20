package com.hawk.oauth.persistence.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.google.common.base.Splitter;
import com.hawk.oauth.core.base.AbstractBaseServiceImpl;
import com.hawk.oauth.persistence.dao.SysDictMapper;
import com.hawk.oauth.persistence.entity.SysDict;
import com.hawk.oauth.persistence.service.SysDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SysDictServiceImpl Service接口实现类
 *
 * @author fy
 * @version 1.0.0 2019-10-22 08:45:49 初始创建
 */

@Slf4j
@Service
public class SysDictServiceImpl extends AbstractBaseServiceImpl implements SysDictService{

    @Autowired
    private SysDictMapper sysDictMapper;

    @Override
    public Long insert(SysDict paramBean) {
        Long newId = getNextId();
        paramBean.setId(newId);
        sysDictMapper.insert(paramBean);
        return newId;
    }

    @Override
    public void insertBatch(List<SysDict> paramBeans) {
        for (SysDict item : paramBeans) {
            Long newId = getNextId();
            item.setId(newId);
        }
        sysDictMapper.insertBatch(paramBeans);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        sysDictMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatchByPrimaryKeys(String ids) {
        List<String> stringList = Splitter.on(",").splitToList(ids);
        List<Long> idLst = stringList.stream().map(c -> Long.parseLong(c)).collect(Collectors.toList());
        deleteBatchByPrimaryKeys(idLst);
    }

    @Override
    public void deleteBatchByPrimaryKeys(List<Long> ids) {
        sysDictMapper.deleteBatchByPrimaryKeys(ids);
    }

    @Override
    public void updateByPrimaryKeySelective(SysDict paramBean) {
        sysDictMapper.updateByPrimaryKeySelective(paramBean);
    }

    @Override
    public SysDict getByPrimaryKey(Long id) {
        return sysDictMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysDict> listByConditions(SysDict paramBean) {
        return sysDictMapper.selectByConditions(paramBean);
    }

    @Override
    public PageInfo<SysDict> listByPage(SysDict paramBean, final int pageNum, final int pageSize) {
        PageMethod.startPage(pageNum, pageSize);
        List<SysDict> lst = sysDictMapper.selectByConditions(paramBean);
        return new PageInfo<>(lst);
    }

}
