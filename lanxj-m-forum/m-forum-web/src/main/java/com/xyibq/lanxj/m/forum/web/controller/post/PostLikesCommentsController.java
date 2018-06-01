package com.xyibq.lanxj.m.forum.web.controller.post;

import com.alibaba.fastjson.JSON;
import com.xyibq.lanxj.m.forum.Rabbitmq.RabbitmqReceiver;
import com.xyibq.lanxj.m.forum.common.util.CheckUtil;
import com.xyibq.lanxj.m.forum.common.util.ResultUtil;
import com.xyibq.lanxj.m.forum.domain.entity.PostCommentsRelateEntity;
import com.xyibq.lanxj.m.forum.domain.entity.PostLikesRelateEntity;
import com.xyibq.lanxj.m.forum.service.PostLikesCommentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/postLC")
public class PostLikesCommentsController {

    private static final Logger logger = LoggerFactory.getLogger(PostLikesCommentsController.class);

    @Resource
    PostLikesCommentsService postLikesCommentsService;
    @Resource
    private AmqpTemplate rabbitTemplate;
    @Resource
    private RabbitmqReceiver rabbitmqReceiver;

    /**
     * 帖子新增点赞 gcr add
     */
    @RequestMapping("/insertPostLike")
    @ResponseBody
    public String insertPostLike(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String newPostLike = request.getParameter("newPostLike");
        PostLikesRelateEntity postLikesRelateEntity = JSON.parseObject(newPostLike,PostLikesRelateEntity.class);

        if(CheckUtil.checkEmpty(postLikesRelateEntity)){
            return ResultUtil.errorMsg();
        }
        if(CheckUtil.checkEmpty(postLikesRelateEntity.getPostId())){
            logger.error("点赞帖子ID不能为空");
            return ResultUtil.errorMsg();
        }
        if(CheckUtil.checkEmpty(postLikesRelateEntity.getLikeUserId())){
            logger.error("点赞用户ID不能为空");
            return ResultUtil.errorMsg();
        }
        if(CheckUtil.checkEmpty(postLikesRelateEntity.getLikeUserName())){
            logger.error("点赞用户姓名不能为空");
            return ResultUtil.errorMsg();
        }
        // 原有注释掉
        // postLikesCommentsService.insertPostLike(postLikesRelateEntity);
        // rabbitTemplate.convertAndSend("MessgeExchange", "addComment",JSON.toJSONString(postLikesRelateEntity));
        // rabbitTemplate.convertAndSend("MessgeExchange", "addLike",JSON.toJSONString(postLikesRelateEntity));

        //gcr add start
        String s = JSON.toJSONString(postLikesRelateEntity);
        System.out.println("gcr:"+s);
        //异步发送点赞信息和关联消息
        postLikesCommentsService.insertPostLikeMq(postLikesRelateEntity);
        //gcr add end

        return ResultUtil.successMsg();

    }

    /**
     * 帖子新增评论gcr
     */
    @RequestMapping("/insertPostComment")
    public String insertPostComment(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String newPostComment = request.getParameter("newPostComment");
        PostCommentsRelateEntity postCommentsRelateEntity = JSON.parseObject(newPostComment,PostCommentsRelateEntity.class);

        if(CheckUtil.checkEmpty(postCommentsRelateEntity)){
            return ResultUtil.errorMsg();
        }
        if(CheckUtil.checkEmpty(postCommentsRelateEntity.getPostId())){
            logger.error("新增评论 帖子ID不能为空");
            return ResultUtil.errorMsg();
        }
        if(CheckUtil.checkEmpty(postCommentsRelateEntity.getCommentUserId())){
            logger.error("新增评论 用户ID不能为空");
            return ResultUtil.errorMsg();
        }
        if(CheckUtil.checkEmpty(postCommentsRelateEntity.getCommentUserName())){
            logger.error("新增评论 用户姓名不能为空");
            return ResultUtil.errorMsg();
        }
        if(CheckUtil.checkEmpty(postCommentsRelateEntity.getCommentContent())){
            return ResultUtil.errorMsg("新增评论 评论内容不能为空");
        }

        postLikesCommentsService.insertPostCommentMq(postCommentsRelateEntity);
        return ResultUtil.successMsg();
    }


    /**
     * 帖子新增点赞  //原有的 加了bak备注
     */
    @RequestMapping("/insertPostLike_bak")
    public String insertPostLike_bak(HttpServletRequest request, HttpServletResponse response) throws Exception{

        String newPostLike = request.getParameter("newPostLike");
        PostLikesRelateEntity postLikesRelateEntity = JSON.parseObject(newPostLike,PostLikesRelateEntity.class);

        if(CheckUtil.checkEmpty(postLikesRelateEntity)){
            return ResultUtil.errorMsg();
        }
        if(CheckUtil.checkEmpty(postLikesRelateEntity.getPostId())){
            logger.error("点赞帖子ID不能为空");
            return ResultUtil.errorMsg();
        }
        if(CheckUtil.checkEmpty(postLikesRelateEntity.getLikeUserId())){
            logger.error("点赞用户ID不能为空");
            return ResultUtil.errorMsg();
        }
        if(CheckUtil.checkEmpty(postLikesRelateEntity.getLikeUserName())){
            logger.error("点赞用户姓名不能为空");
            return ResultUtil.errorMsg();
        }
        postLikesCommentsService.insertPostLike(postLikesRelateEntity);

        //异步发送点赞信息
        rabbitTemplate.convertAndSend("MessgeExchange", "addLike",JSON.toJSONString(postLikesRelateEntity));
        return ResultUtil.successMsg();

    }

    /**
     * 帖子取消点赞
     */
    @RequestMapping("/cancelPostToNoLike")
    public String cancelPostToNoLike(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String cancelPostLike = request.getParameter("cancelPostLike");
        PostLikesRelateEntity postLikesRelateEntity = JSON.parseObject(cancelPostLike,PostLikesRelateEntity.class);
        if(CheckUtil.checkEmpty(postLikesRelateEntity)){
            return ResultUtil.errorMsg();
        }
        if(CheckUtil.checkEmpty(postLikesRelateEntity.getLikeUserId())){
            logger.error("点赞用户ID不能为空");
            return ResultUtil.errorMsg();
        }
        if(CheckUtil.checkEmpty(postLikesRelateEntity.getPostId())){
            logger.error("点赞帖子ID不能为空");
            return ResultUtil.errorMsg();
        }

        postLikesCommentsService.cancelPostToNoLike(postLikesRelateEntity);
        return ResultUtil.successMsg();
    }

    /**
     * 帖子新增评论
     */
    @RequestMapping("/insertPostComment_bak")
    public String insertPostComment_bak(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String newPostComment = request.getParameter("newPostComment");
        PostCommentsRelateEntity postCommentsRelateEntity = JSON.parseObject(newPostComment,PostCommentsRelateEntity.class);

        if(CheckUtil.checkEmpty(postCommentsRelateEntity)){
            return ResultUtil.errorMsg();
        }
        if(CheckUtil.checkEmpty(postCommentsRelateEntity.getPostId())){
            logger.error("新增评论 帖子ID不能为空");
            return ResultUtil.errorMsg();
        }
        if(CheckUtil.checkEmpty(postCommentsRelateEntity.getCommentUserId())){
            logger.error("新增评论 用户ID不能为空");
            return ResultUtil.errorMsg();
        }
        if(CheckUtil.checkEmpty(postCommentsRelateEntity.getCommentUserName())){
            logger.error("新增评论 用户姓名不能为空");
            return ResultUtil.errorMsg();
        }
        if(CheckUtil.checkEmpty(postCommentsRelateEntity.getCommentContent())){
            return ResultUtil.errorMsg("新增评论 评论内容不能为空");
        }

        postLikesCommentsService.insertPostComment(postCommentsRelateEntity);
        return ResultUtil.successMsg();
    }

    /**
     * 删除帖子评论
     */
    @RequestMapping("/deletePostComment")
    public String deletePostComment(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String delPostComment = request.getParameter("delPostComment");
        PostCommentsRelateEntity postCommentsRelateEntity = JSON.parseObject(delPostComment,PostCommentsRelateEntity.class);

        if(CheckUtil.checkEmpty(postCommentsRelateEntity)){
            return ResultUtil.errorMsg();
        }
        if(CheckUtil.checkEmpty(postCommentsRelateEntity.getPostId())){
            logger.error("删除评论 帖子ID不能为空");
            return ResultUtil.errorMsg();
        }
        if(CheckUtil.checkEmpty(postCommentsRelateEntity.getCommentUserId())){
            logger.error("删除评论 用户ID不能为空");
            return ResultUtil.errorMsg();
        }

        postLikesCommentsService.deletePostComment(postCommentsRelateEntity);
        return ResultUtil.successMsg();
    }

}
