package com.xyibq.lanxj.admin.forum.web.controller.postInfo;

import com.xyibq.lanxj.admin.forum.common.util.CheckUtil;
import com.xyibq.lanxj.admin.forum.common.util.ResultUtil;
import com.xyibq.lanxj.admin.forum.domain.entity.ForumTopicInfoEntity;
import com.xyibq.lanxj.admin.forum.service.ForumTopicInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/topicInfo")
public class ForumTopicController {

    private static final Logger logger = LoggerFactory.getLogger(ForumTopicController.class);
    @Resource
    ForumTopicInfoService forumTopicInfoService;

    /**
     * 查询系统所有版块列表
     */
    @ResponseBody
    @RequestMapping("/queryAllForumTopicList")
    public String  queryAllForumTopicList(){

        List<ForumTopicInfoEntity> topicList= new ArrayList<ForumTopicInfoEntity>();
        try {
            topicList = forumTopicInfoService.queryAllForumTopicList();
        } catch (Exception e) {
            logger.error("查询系统所有版块列表 失败!",e);
            ResultUtil.errorMsg();
        }

        Map<String,List> map = new HashMap<String,List>();
        map.put("topicList",topicList);
        return ResultUtil.successMsg(topicList);
    }

    /**
     * 修改版块信息
     */
    @ResponseBody
    @RequestMapping("/updateForumTopicInfo")
    public String updateForumTopicInfo(ForumTopicInfoEntity forumTopicInfoEntity){

        try {
            forumTopicInfoService.updateForumTopicInfo(forumTopicInfoEntity);
        } catch (Exception e) {
            logger.error("查询系统所有版块列表 失败!",e);
            ResultUtil.errorMsg();
        }

        return ResultUtil.successMsg();
    }

    /**
     * 新增版块信息
     */
    @ResponseBody
    @RequestMapping("/addTopicInfo")
    public String addTopicInfo(ForumTopicInfoEntity forumTopicInfoEntity){

        if(forumTopicInfoEntity == null){
            logger.error(" 新增版块信息 入参为空！");
            return ResultUtil.errorMsg();
        }
        if(CheckUtil.checkEmpty(forumTopicInfoEntity.getTopicName())){
            return ResultUtil.errorMsg("新增版块 版块名称不可为空！");
        }
        if(CheckUtil.checkEmpty(forumTopicInfoEntity.getIconUrl())){
            return ResultUtil.errorMsg("新增版块 图标不可为空！");
        }

        try {
            forumTopicInfoService.addTopicInfo(forumTopicInfoEntity);
        } catch (Exception e) {
            logger.error("系统新增版块 失败!",e);
            ResultUtil.errorMsg();
        }

        return ResultUtil.successMsg();
    }
    /**
     *  版块发帖审核开关
     */
    @ResponseBody
    @RequestMapping("/topicCheckPostSwitch")
    public String topicCheckPostSwitch(int topicId,int checkYn){

        try {
            forumTopicInfoService.topicCheckPostSwitch(topicId,checkYn);
        } catch (Exception e) {
            logger.error("查询系统所有版块列表 失败!",e);
            ResultUtil.errorMsg();
        }
        return ResultUtil.successMsg();
    }

    /**
     *  匿名发贴版块开关
     */
    @ResponseBody
    @RequestMapping("/topicAnonymitySwitch")
    public String topicAnonymitySwitch(int topicId,int anonymityYn){

        try {
            forumTopicInfoService.topicAnonymitySwitch(topicId,anonymityYn);
        } catch (Exception e) {
            logger.error("查询系统所有版块列表 失败!",e);
            ResultUtil.errorMsg();
        }
        return ResultUtil.successMsg();
    }

    /**
     * 版块开关
     */
    @ResponseBody
    @RequestMapping("/topicStatusSwitch")
    public String topicStatusSwitch(int topicId,int topicStatus){

        try {
            forumTopicInfoService.topicStatusSwitch(topicId,topicStatus);
        } catch (Exception e) {
            logger.error("查询系统所有版块列表 失败!",e);
            ResultUtil.errorMsg();
        }
        return ResultUtil.successMsg();
    }

}
