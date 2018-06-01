package com.xyibq.lanxj.admin.forum.web.controller.user;

import com.xyibq.lanxj.admin.forum.common.util.ResultUtil;
import com.xyibq.lanxj.admin.forum.domain.vo.UserRoleMenuVo;
import com.xyibq.lanxj.admin.forum.mapper.RoleMenuMapper;
import com.xyibq.lanxj.admin.forum.service.RoleMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserRoleMenuController {

    @Resource
    RoleMenuService roleMenuService;

    @RequestMapping("getUserRoleMenu")
    @ResponseBody
    public String getUserRoleMenu(String userId){
        List<UserRoleMenuVo>  userRoleMenuVos = roleMenuService.queryUserRoleMenuList(userId);
        return ResultUtil.successMsg(userRoleMenuVos);
    }
}
