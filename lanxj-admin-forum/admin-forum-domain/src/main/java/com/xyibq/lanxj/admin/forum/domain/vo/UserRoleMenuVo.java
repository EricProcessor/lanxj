package com.xyibq.lanxj.admin.forum.domain.vo;

import java.io.Serializable;

public class UserRoleMenuVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 菜单ID
     */
    private String menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单等级
     */
    private String menuLevel;

    /**
     * 菜单排序
     */
    private  String sort;

    /**
     * 父节点
     */
    private  String parentId;

    /**
     * 可用图标
     */
    private String iconY;

    /**
     * 不可用图标
     */
    private String iconN;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(String menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getIconY() {
        return iconY;
    }

    public void setIconY(String iconY) {
        this.iconY = iconY;
    }

    public String getIconN() {
        return iconN;
    }

    public void setIconN(String iconN) {
        this.iconN = iconN;
    }
}
