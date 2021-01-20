package com.hawk.oauth.persistence.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.google.common.base.Splitter;
import com.hawk.oauth.core.base.AbstractBaseServiceImpl;
import com.hawk.oauth.persistence.dao.SysRoleMenuMapper;
import com.hawk.oauth.persistence.entity.SysRoleMenu;
import com.hawk.oauth.persistence.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: SysRoleMenuServiceImpl
 * @ProjectName spring-safety-training
 * @Author May
 * @Date 2020/3/28 10:48
 */
@Service
public class SysRoleMenuServiceImpl extends AbstractBaseServiceImpl implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public Long insert(SysRoleMenu paramBean) {
        Long newId = getNextId();
        paramBean.setId(newId);
        sysRoleMenuMapper.insert(paramBean);
        return newId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBatch(List<SysRoleMenu> paramBeans) {
        if (!CollectionUtils.isEmpty(paramBeans)) {
            SysRoleMenu sysRoleMenu = paramBeans.get(0);
            deleteByRoleId(sysRoleMenu.getRoleId());
            for (SysRoleMenu item : paramBeans) {
                Long newId = getNextId();
                item.setId(newId);
            }
            sysRoleMenuMapper.insertBatch(paramBeans);
        }

    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        sysRoleMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatchByPrimaryKeys(String ids) {
        List<String> stringList = Splitter.on(",").splitToList(ids);
        List<Long> idLst = stringList.stream().map(c -> Long.parseLong(c)).collect(Collectors.toList());
        deleteBatchByPrimaryKeys(idLst);
    }

    @Override
    public void deleteBatchByPrimaryKeys(List<Long> ids) {
        sysRoleMenuMapper.deleteBatchByPrimaryKeys(ids);
    }

    @Override
    public void updateByPrimaryKeySelective(SysRoleMenu paramBean) {
        sysRoleMenuMapper.updateByPrimaryKeySelective(paramBean);
    }

    @Override
    public SysRoleMenu getByPrimaryKey(Long id) {
        return sysRoleMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysRoleMenu> listByConditions(SysRoleMenu paramBean) {
        return sysRoleMenuMapper.selectByConditions(paramBean);
    }

    @Override
    public PageInfo<SysRoleMenu> listByPage(SysRoleMenu paramBean,
                                            final int pageNum,
                                            final int pageSize) {
        PageMethod.startPage(pageNum, pageSize);
        List<SysRoleMenu> lst = sysRoleMenuMapper.selectByConditions(paramBean);
        return new PageInfo<>(lst);
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        sysRoleMenuMapper.deleteByRoleId(roleId);
    }

    @Override
    public void deleteBatchByRoleIds(List<Long> ids) {
        sysRoleMenuMapper.deleteBatchByRoleIds(ids);
    }
}
