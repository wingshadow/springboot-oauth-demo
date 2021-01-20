package com.hawk.admin.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hawk.common.base.BaseBean;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * SysRole Bean定义类，对应表sys_role
 * 
 * @author fy
 * @version 1.0.0 2019-10-22 08:43:38 初始创建
 */
@Getter
@Setter
public class SysRole extends BaseBean {

    /**
     * 编号
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    
    /**
     * 角色名称
     */
    private String name;
    
    /**
     * 备注
     */
    private String remark;
    
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
    

}