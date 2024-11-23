package com.yr.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName menu
 */
@TableName(value ="menu")
@Data
public class Menu implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 菜单编码
     */
    private String menucode;

    /**
     * 菜单名字
     */
    private String menuname;

    /**
     * 菜单级别
     */
    private String menulevel;

    /**
     * 菜单的父code
     */
    private String menuparentcode;

    /**
     * 点击触发的函数/跳转的路由
     */
    private String menuclick;

    /**
     * 权限 0超级管理员，1管理员，2普通用户,可以用逗号组合使用
     */
    private String menuright;

    /**
     * 
     */
    private String menucomponent;

    /**
     * 
     */
    private String menuicon;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Menu other = (Menu) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMenucode() == null ? other.getMenucode() == null : this.getMenucode().equals(other.getMenucode()))
            && (this.getMenuname() == null ? other.getMenuname() == null : this.getMenuname().equals(other.getMenuname()))
            && (this.getMenulevel() == null ? other.getMenulevel() == null : this.getMenulevel().equals(other.getMenulevel()))
            && (this.getMenuparentcode() == null ? other.getMenuparentcode() == null : this.getMenuparentcode().equals(other.getMenuparentcode()))
            && (this.getMenuclick() == null ? other.getMenuclick() == null : this.getMenuclick().equals(other.getMenuclick()))
            && (this.getMenuright() == null ? other.getMenuright() == null : this.getMenuright().equals(other.getMenuright()))
            && (this.getMenucomponent() == null ? other.getMenucomponent() == null : this.getMenucomponent().equals(other.getMenucomponent()))
            && (this.getMenuicon() == null ? other.getMenuicon() == null : this.getMenuicon().equals(other.getMenuicon()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMenucode() == null) ? 0 : getMenucode().hashCode());
        result = prime * result + ((getMenuname() == null) ? 0 : getMenuname().hashCode());
        result = prime * result + ((getMenulevel() == null) ? 0 : getMenulevel().hashCode());
        result = prime * result + ((getMenuparentcode() == null) ? 0 : getMenuparentcode().hashCode());
        result = prime * result + ((getMenuclick() == null) ? 0 : getMenuclick().hashCode());
        result = prime * result + ((getMenuright() == null) ? 0 : getMenuright().hashCode());
        result = prime * result + ((getMenucomponent() == null) ? 0 : getMenucomponent().hashCode());
        result = prime * result + ((getMenuicon() == null) ? 0 : getMenuicon().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", menucode=").append(menucode);
        sb.append(", menuname=").append(menuname);
        sb.append(", menulevel=").append(menulevel);
        sb.append(", menuparentcode=").append(menuparentcode);
        sb.append(", menuclick=").append(menuclick);
        sb.append(", menuright=").append(menuright);
        sb.append(", menucomponent=").append(menucomponent);
        sb.append(", menuicon=").append(menuicon);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}