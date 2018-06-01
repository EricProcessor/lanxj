package com.xyibq.lanxj.admin.forum.web.controller.user;

import com.alibaba.fastjson.JSON;
import com.xyibq.lanxj.admin.forum.common.util.ResultUtil;
import com.xyibq.lanxj.admin.forum.domain.entity.UserTopicAuthEntity;
import com.xyibq.lanxj.admin.forum.service.UserTopicAuthService;
//import com.xyibq.lanxj.admin.forum.web.controller.sensitiveWord.SensitiveWordController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserTopicAuthController {

    private static final Logger logger = LoggerFactory.getLogger(UserTopicAuthController.class);

    @Resource
    UserTopicAuthService userTopicAuthService;

    /**
     * 用户分配版块权限
     */
    @ResponseBody
    @RequestMapping("/insertUserTopicAuth")
    public String insertUserTopicAuth(HttpServletRequest request){
        String addUserAuthTopicListStr = request.getParameter("addUserAuthTopicList");
        List<UserTopicAuthEntity>  addUserAuthTopicList = JSON.parseArray(addUserAuthTopicListStr,UserTopicAuthEntity.class);

        //获取当前登录人
//        String loginUserId = getLoginUserId();
        for(UserTopicAuthEntity addEntity : addUserAuthTopicList){
            addEntity.setAuthStatus("1");
            addEntity.setCreateUser("10002");
        }

        try {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("createUser","10002");
            map.put("list",addUserAuthTopicList);
            userTopicAuthService.insertUserTopicAuth(map);
        } catch (Exception e) {
            logger.error("版主新增权限失败！",e);
            return ResultUtil.errorMsg();
        }

        try {
            Map<String,Object> umap = new HashMap<String,Object>();
            umap.put("modifyUser","10003");
            umap.put("list",addUserAuthTopicList);
            userTopicAuthService.updateUserTopicAuth(umap);
        } catch (Exception e) {
            logger.error("版主删除其他权限失败！",e);
            return ResultUtil.errorMsg();
        }

        return ResultUtil.successMsg();
    }


}
