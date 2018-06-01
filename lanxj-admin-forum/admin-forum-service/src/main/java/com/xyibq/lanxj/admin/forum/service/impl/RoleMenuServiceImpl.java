package com.xyibq.lanxj.admin.forum.service.impl;

import com.xyibq.lanxj.admin.forum.domain.vo.UserRoleMenuVo;
import com.xyibq.lanxj.admin.forum.mapper.RoleMenuMapper;
import com.xyibq.lanxj.admin.forum.service.RoleMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Resource
    RoleMenuMapper roleMenuMapper;

    /**
     * 查询用户角色菜单
     */
    public List<UserRoleMenuVo> queryUserRoleMenuList(String userId){
        return  roleMenuMapper.selectUserRoleMenuList(userId);
    }


    /**
     * 查询用户角色
     */
    public String queryUserRole(String userId){
        return  roleMenuMapper.selectUserRole(userId);
    }
}
