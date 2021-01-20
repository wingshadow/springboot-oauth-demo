package com.hawk.oauth.persistence.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.google.common.base.Splitter;
import com.hawk.oauth.core.base.AbstractBaseServiceImpl;
import com.hawk.oauth.persistence.dao.SysDeptMapper;
import com.hawk.oauth.persistence.entity.SysDept;
import com.hawk.oauth.persistence.service.SysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: SysDeptServiceImpl
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/27 14:35
 */
@Slf4j
@Service
public class SysDeptServiceImpl extends AbstractBaseServiceImpl implements SysDeptService {

    @Resource
    private SysDeptMapper sysDeptMapper;


    @Override
    public Long insert(SysDept paramBean) {
        Long newId = getNextId();
        paramBean.setId(newId);
        sysDeptMapper.insert(paramBean);
        return newId;
    }

    @Override
    public void insertBatch(List<SysDept> paramBeans) {
        for (SysDept item : paramBeans) {
            Long newId = getNextId();
            item.setId(newId);
        }
        sysDeptMapper.insertBatch(paramBeans);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        sysDeptMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatchByPrimaryKeys(String ids) {
        List<String> stringList = Splitter.on(",").splitToList(ids);
        List<Long> idLst = stringList.stream().map(c -> Long.parseLong(c)).collect(Collectors.toList());
        deleteBatchByPrimaryKeys(idLst);
    }

    @Override
    public void deleteBatchByPrimaryKeys(List<Long> ids) {
        sysDeptMapper.deleteBatchByPrimaryKeys(ids);
    }

    @Override
    public void updateByPrimaryKeySelective(SysDept paramBean) {
        sysDeptMapper.updateByPrimaryKeySelective(paramBean);
    }

    @Override
    public SysDept getByPrimaryKey(Long id) {
        return sysDeptMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysDept> listByConditions(SysDept paramBean) {
        return sysDeptMapper.selectByConditions(paramBean);
    }

    @Override
    public PageInfo<SysDept> listByPage(SysDept paramBean, final int pageNum,
                                        final int pageSize) {
        PageMethod.startPage(pageNum, pageSize);
        List<SysDept> lst = sysDeptMapper.selectByConditions(paramBean);
        return new PageInfo<>(lst);
    }

    @Override
    public List<SysDept> findAllRecursion() {
        return sysDeptMapper.findAllRecursion();
    }

    @Override
    public List<SysDept> findDeptRecursion(Long parentId) {
        return sysDeptMapper.findDeptRecursion(parentId);
    }
}
