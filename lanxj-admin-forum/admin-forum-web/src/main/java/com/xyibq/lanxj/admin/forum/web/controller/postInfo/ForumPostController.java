package com.xyibq.lanxj.admin.forum.web.controller.postInfo;

import com.xyibq.lanxj.admin.forum.common.util.CheckUtil;
import com.xyibq.lanxj.admin.forum.common.util.ResultUtil;
import com.xyibq.lanxj.admin.forum.domain.vo.ForumPostInfoDetailVo;
import com.xyibq.lanxj.admin.forum.service.ForumPostInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/postInfo")
public class ForumPostController {

    private static final Logger logger = LoggerFactory.getLogger(ForumPostController.class);

    @Resource
    ForumPostInfoService forumPostInfoService;

    @RequestMapping("/")
    public  String test(){
        return "test/PostInfoTest";
    }

    /**
     * 将帖子设为置顶
     */
    @ResponseBody
    @RequestMapping("/setPostToTop")
    public String  setPostToTop(String postId){

        // 校验id
        if (CheckUtil.checkEmpty(postId)){
            return ResultUtil.errorMsg("帖子ID不能为空");
        }

        try {
            forumPostInfoService.setPostToTop(postId);
        } catch (Exception e) {
           logger.error("帖子{}设为置顶 失败！",postId,e);
           return ResultUtil.errorMsg();
        }

        return  ResultUtil.successMsg();
    }

    /**
     * 将帖子取消置顶
     */
    @ResponseBody
    @RequestMapping("/cancelPostTop")
    public String  cancelPostTop(String postId){

        if (CheckUtil.checkEmpty(postId)){
            return ResultUtil.errorMsg("帖子ID不能为空");
        }
        try {
            forumPostInfoService.cancelPostTop(postId);
        } catch (Exception e) {
            logger.error("帖子{}取消置顶 失败！",postId,e);
            return ResultUtil.errorMsg();
        }

        return  ResultUtil.successMsg();
    }

    /**
     * 查询版块帖子列表
     */
    @ResponseBody
    @RequestMapping("/queryTopicPostList")
    public String  queryTopicPostList(String topic){

        if (CheckUtil.checkEmpty(topic)){
            return ResultUtil.errorMsg("帖子ID不能为空");
        }

        try {
            forumPostInfoService.queryTopicPostList(topic);
        } catch (Exception e) {
            logger.error("查询版块{}帖子列表 失败！",topic,e);
            return ResultUtil.errorMsg();
        }

        return  ResultUtil.successMsg();
    }

    /**
     * 查询帖子详情
     */
    @ResponseBody
    @RequestMapping("/queryPostDetail")
    public String  queryPostDetail(String postId){

        if (CheckUtil.checkEmpty(postId)) {
            logger.error("帖子ID不能为空");
            return ResultUtil.errorMsg();
        }

        ForumPostInfoDetailVo forumPostInfoDetailVo = new ForumPostInfoDetailVo();
        try {
            forumPostInfoService.queryPostDetail(postId);
        } catch (Exception e) {
            logger.error("管理员删除帖子{}失败！",postId);
            return ResultUtil.errorMsg();
        }

        return ResultUtil.successMsg(forumPostInfoDetailVo);
    }
    /**
     * 删除帖子
     */
    @ResponseBody
    @RequestMapping("/removePostInfoByAdmin")
    public String  removePostInfoByAdmin(String postId){

        if (CheckUtil.checkEmpty(postId)) {
            return ResultUtil.errorMsg("帖子ID不能为空");
        }
        try {
            forumPostInfoService.removePostInfoByAdmin(postId);
        } catch (Exception e) {
            logger.error("管理员删除帖子{}失败！",postId);
            return ResultUtil.errorMsg();
        }

        return ResultUtil.successMsg();
    }
}
