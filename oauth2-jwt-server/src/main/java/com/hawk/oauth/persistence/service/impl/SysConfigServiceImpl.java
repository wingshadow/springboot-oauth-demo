package com.hawk.oauth.persistence.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.google.common.base.Splitter;
import com.hawk.oauth.core.base.AbstractBaseServiceImpl;
import com.hawk.oauth.persistence.dao.SysConfigMapper;
import com.hawk.oauth.persistence.entity.SysConfig;
import com.hawk.oauth.persistence.service.SysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: SysConfigServiceImpl
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/27 11:14
 */
@Slf4j
@Service
public class SysConfigServiceImpl extends AbstractBaseServiceImpl implements SysConfigService {

    @Autowired
    private SysConfigMapper sysConfigMapper;

    @Override
    public Long insert(SysConfig paramBean) {
        Long newId = getNextId();
        paramBean.setId(newId);
        sysConfigMapper.insert(paramBean);
        return newId;
    }

    @Override
    public void insertBatch(List<SysConfig> paramBeans) {
        for (SysConfig item : paramBeans) {
            Long newId = getNextId();
            item.setId(newId);
        }
        sysConfigMapper.insertBatch(paramBeans);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        sysConfigMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatchByPrimaryKeys(String ids) {
        List<String> stringList = Splitter.on(",").splitToList(ids);
        List<Long> idLst = stringList.stream().map(c -> Long.parseLong(c)).collect(Collectors.toList());
        deleteBatchByPrimaryKeys(idLst);
    }

    @Override
    public void deleteBatchByPrimaryKeys(List<Long> ids) {
        sysConfigMapper.deleteBatchByPrimaryKeys(ids);
    }

    @Override
    public void updateByPrimaryKeySelective(SysConfig paramBean) {
        sysConfigMapper.updateByPrimaryKeySelective(paramBean);
    }

    @Override
    public SysConfig getByPrimaryKey(Long id) {
        return sysConfigMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysConfig> listByConditions(SysConfig paramBean) {
        return sysConfigMapper.selectByConditions(paramBean);
    }

    @Override
    public PageInfo<SysConfig> listByPage(SysConfig paramBean, int pageNum, int pageSize) {
        PageMethod.startPage(pageNum, pageSize);
        List<SysConfig> lst = sysConfigMapper.selectByConditions(paramBean);
        return new PageInfo<>(lst);
    }
}
