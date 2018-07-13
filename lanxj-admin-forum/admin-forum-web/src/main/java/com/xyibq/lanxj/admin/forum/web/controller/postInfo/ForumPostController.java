package com.xyibq.lanxj.admin.forum.web.controller.postInfo;

import com.alibaba.fastjson.JSON;
import com.xyibq.lanxj.admin.forum.common.util.CheckUtil;
import com.xyibq.lanxj.admin.forum.common.util.ResultUtil;
import com.xyibq.lanxj.admin.forum.domain.entity.ForumPostInfoEntity;
import com.xyibq.lanxj.admin.forum.domain.entity.ForumTopicInfoEntity;
import com.xyibq.lanxj.admin.forum.domain.entity.MyMessageDetailEntity;
import com.xyibq.lanxj.admin.forum.domain.entity.PostCommentsRelateEntity;
import com.xyibq.lanxj.admin.forum.domain.vo.ForumPostInfoDetailVo;
import com.xyibq.lanxj.admin.forum.service.ForumPostInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/postInfo")
public class ForumPostController {

    private static final Logger logger = LoggerFactory.getLogger(ForumPostController.class);

    @Resource
    ForumPostInfoService forumPostInfoService;

    @Resource
    AmqpTemplate rabbitTemplate;

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


    /**
     * 审核帖子 修改帖子状态为拒绝30 同时消息推送提醒
     */
    @ResponseBody
    @RequestMapping("/modifyPostInfoBypostId")
    public String modifyPostInfoBypostId(String postId){

        if (CheckUtil.checkEmpty(postId)) {
            return ResultUtil.errorMsg("帖子ID不能为空");
        }
        try {
            forumPostInfoService.modifyPostInfoBypostId(postId);
        } catch (Exception e) {
            logger.error("管理员拒绝帖子{}失败！",postId);
            return ResultUtil.errorMsg();
        }

        MyMessageDetailEntity myMessageDetailEntity=null;
        try {
            ForumPostInfoEntity forumPostInfoEntity = forumPostInfoService.queryPostInfoDetailByPostId(postId);
            if(forumPostInfoEntity!=null){
                //组装要发送的消息内容
               myMessageDetailEntity= copypropertyToMessageDtl(forumPostInfoEntity);
            }

        } catch (Exception e) {
            logger.error("管理员查询帖子{}失败！",postId);
            return ResultUtil.errorMsg();
        }

        //使用已封装好的convertAndSend(String exchange , String routingKey , Object message)使用特定的routingKey发送消息到指定的exchange
        rabbitTemplate.convertAndSend("MessgeExchange" , "MrejectPostinfo" , JSON.toJSONString(myMessageDetailEntity));

        return ResultUtil.successMsg();
    }


    /**
     * 数据统计——版块【评论】前五名
     */
    @ResponseBody
    @RequestMapping("/queryTopCommentCount")
    public String queryTopCommentCount(String topicId){
        List<ForumTopicInfoEntity> topicList = new ArrayList<ForumTopicInfoEntity>();
        try {
            topicList =  forumPostInfoService.queryTopCommentCount(topicId);
        } catch (Exception e) {
            logger.error("数据统计——版块{} 查询【评论】前五名 ",topicId);
            return ResultUtil.errorMsg();
        }
        return ResultUtil.successMsg(topicList);
    }

    /**
     * 数据统计——版块【点赞】前五名
     */
    @ResponseBody
    @RequestMapping("/queryTopLikeCount")
    public String queryTopLikeCount(String topicId){

        List<ForumTopicInfoEntity> topicList =  new ArrayList<ForumTopicInfoEntity>();
        try {
            topicList =  forumPostInfoService.queryTopLikeCount(topicId);
        } catch (Exception e) {
            logger.error("数据统计——版块{} 查询【点赞】前五名 ",topicId);
            return ResultUtil.errorMsg();
        }
        return ResultUtil.successMsg(topicList);
    }

    /**
     * 数据统计——版块【浏览】前五名
     */
    @ResponseBody
    @RequestMapping("/queryTopPVCount")
    public  String queryTopPVCount(String topicId){

        List<ForumTopicInfoEntity> topicList =  new ArrayList<ForumTopicInfoEntity>();
        try {
            topicList =  forumPostInfoService.queryTopLikeCount(topicId);
        } catch (Exception e) {
            logger.error("数据统计——版块{} 查询【浏览】前五名 ",topicId);
            return ResultUtil.errorMsg();
        }
        return ResultUtil.successMsg(topicList);
    }


    private MyMessageDetailEntity copypropertyToMessageDtl(ForumPostInfoEntity forumPostInfoEntity){

        MyMessageDetailEntity entity=new MyMessageDetailEntity();
        entity.setUserId(new Long(99999));//用户ID=管理员默认设置id 99999
        entity.setMsgId(new Long(80));//消息ID--帖子拒绝
        entity.setReadYn(new Long(0));//是否已读
        entity.setPostId(forumPostInfoEntity.getPostId());//帖子ID
        entity.setPostuserId(forumPostInfoEntity.getUserId());//发帖人ID
        entity.setPostuserName(forumPostInfoEntity.getUserName());//发帖人用户姓名
        //entity.setAttentuserId();//关注帖子ID的用户ID

        if(forumPostInfoEntity.getPostContent().length()<10){
            String postcontent=  forumPostInfoEntity.getPostContent().substring(0,forumPostInfoEntity.getPostContent().length());
            entity.setPostPrefixContext(postcontent);
        }else{
            entity.setPostPrefixContext(forumPostInfoEntity.getPostContent().substring(0,10));
        }
        entity.setCreateTime(new Date());
        return entity;
    }


}
