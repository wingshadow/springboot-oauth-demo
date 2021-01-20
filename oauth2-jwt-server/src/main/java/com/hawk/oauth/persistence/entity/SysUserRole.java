package com.hawk.oauth.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hawk.oauth.bean.BaseBean;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * SysUserRole Bean定义类，对应表sys_user_role
 * 
 * @author fy
 * @version 1.0.0 2019-10-22 08:38:50 初始创建
 */
@Getter
@Setter
public class SysUserRole extends BaseBean {

    /**
     * 编号
     */
    private Long id;
    
    /**
     * 用户ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;
    
    /**
     * 角色ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long roleId;
    
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
    

}