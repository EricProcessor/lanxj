package com.xyibq.lanxj.admin.forum.service;

import com.xyibq.lanxj.admin.forum.domain.vo.UserRoleMenuVo;

import java.util.List;

public interface RoleMenuService {

    /**
     * 查询用户角色菜单
     */
    public List<UserRoleMenuVo> queryUserRoleMenuList(String userId);


    /**
     * 查询用户角色
     */
    public String queryUserRole(String userId);
}
