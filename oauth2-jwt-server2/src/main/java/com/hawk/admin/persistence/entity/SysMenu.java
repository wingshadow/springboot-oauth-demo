package com.hawk.admin.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hawk.common.base.BaseBean;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * SysMenu Bean定义类，对应表sys_menu
 * 
 * @author fy
 * @version 1.0.0 2019-10-22 08:44:49 初始创建
 */
@Getter
@Setter
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class SysMenu extends BaseBean {

    /**
     * ID
     * 前端转换Long精度不够
     * 先把主键转换为字符串类型
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    
    /**
     * 菜单名称
     */
    private String name;
    
    /**
     * 父菜单ID，一级菜单为0
     */
    private Long parentId;
    
    /**
     * 菜单URL,类型：1.普通页面（如用户管理， /sys/user） 2.嵌套完整外部页面，以http(s)开头的链接 3.嵌套服务器页面，使用iframe:前缀+目标URL(如SQL监控， iframe:/druid/login.html, iframe:前缀会替换成服务器地址)
     */
    private String url;
    
    /**
     * 授权(多个用逗号分隔，如：sys:user:add,sys:user:edit)
     */
    private String perms;
    
    /**
     * 类型   0：目录   1：菜单   2：按钮
     */
    private Integer type;
    
    /**
     * 菜单图标
     */
    private String icon;
    
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
     * 子菜单信息
     */
    private List<SysMenu> children;
}