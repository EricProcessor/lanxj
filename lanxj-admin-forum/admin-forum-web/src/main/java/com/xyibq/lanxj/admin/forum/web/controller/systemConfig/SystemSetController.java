package com.xyibq.lanxj.admin.forum.web.controller.systemConfig;


import com.xyibq.lanxj.admin.forum.domain.vo.UserTalentVo;
import com.xyibq.lanxj.admin.forum.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/systemset")
public class SystemSetController {


    @Resource
    UserInfoService userInfoService;

    //当月榜单
    @RequestMapping(value = "/currentDateFirstFive")
    @ResponseBody
    public  String currentDateFirstFive() throws Exception {


        userInfoService.UserTalentList("");
        return null;
    }
}
