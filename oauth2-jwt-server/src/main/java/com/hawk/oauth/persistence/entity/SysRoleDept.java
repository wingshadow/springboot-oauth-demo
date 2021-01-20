package com.hawk.oauth.persistence.entity;

import com.hawk.oauth.bean.BaseBean;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * SysRoleDept Bean定义类，对应表sys_role_dept
 * 
 * @author fy
 * @version 1.0.0 2019-10-22 08:42:07 初始创建
 */
@Getter
@Setter
public class SysRoleDept extends BaseBean {

    /**
     * 编号
     */
    private Long id;
    
    /**
     * 角色ID
     */
    private Long roleId;
    
    /**
     * 机构ID
     */
    private Long deptId;
    
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