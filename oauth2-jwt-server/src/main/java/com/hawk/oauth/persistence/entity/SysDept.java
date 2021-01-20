package com.hawk.oauth.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hawk.oauth.bean.BaseBean;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * SysDept Bean定义类，对应表sys_dept
 * 
 * @author fy
 * @version 1.0.0 2019-10-22 08:47:10 初始创建
 */
@Getter
@Setter
public class SysDept extends BaseBean {

    /**
     * 编号
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    
    /**
     * 机构名称
     */
    private String name;
    
    /**
     * 上级机构ID，一级机构为0
     */
    private Long parentId;
    
    /**
     * 排序
     */
    private Integer orderNum;
    
    /**
     * 创建人
     */
    private String createBy;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 更新人
     */
    private String lastUpdateBy;
    
    /**
     * 更新时间
     */
    private Date lastUpdateTime;
    
    /**
     * 是否删除  -1：已删除  0：正常
     */
    private String delFlag;

    /**
     * 子部门信息
     */
    private List<SysDept> children;
}