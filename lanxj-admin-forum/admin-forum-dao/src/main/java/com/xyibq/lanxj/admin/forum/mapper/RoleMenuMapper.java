package com.xyibq.lanxj.admin.forum.mapper;

import com.xyibq.lanxj.admin.forum.domain.vo.UserRoleMenuVo;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface RoleMenuMapper {

    /**
     * 查询用户角色菜单
     */
    public List<UserRoleMenuVo> selectUserRoleMenuList(String userId);

    /**
     * 查询用户角色
     */
    public  String  selectUserRole(String userId);
}
