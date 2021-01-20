package com.hawk.admin.persistence.entity;


import com.hawk.common.base.BaseBean;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * SysRoleMenu Bean定义类，对应表sys_role_menu
 * 
 * @author fy
 * @version 1.0.0 2019-10-22 08:41:02 初始创建
 */
@Getter
@Setter
public class SysRoleMenu extends BaseBean {

    /**
     * 编号
     */
    private Long id;
    
    /**
     * 角色ID
     */
    private Long roleId;
    
    /**
     * 菜单ID
     */
    private Long menuId;
    
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