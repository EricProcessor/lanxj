package com.xyibq.lanxj.m.forum.service.impl;

import com.alibaba.fastjson.JSON;
import com.xyibq.lanxj.m.forum.domain.entity.*;
import com.xyibq.lanxj.m.forum.mapper.AttentionPostMapper;
import com.xyibq.lanxj.m.forum.mapper.ForumPostInfoMapper;
import com.xyibq.lanxj.m.forum.mapper.PostCommentsRelateMapper;
import com.xyibq.lanxj.m.forum.mapper.PostLikesRelateMapper;
import com.xyibq.lanxj.m.forum.service.PostLikesCommentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 帖子点赞、评论服务
 */
@Service
public class PostLikesCommentsServiceImpl implements PostLikesCommentsService {

    private static final Logger logger = LoggerFactory.getLogger(PostLikesCommentsServiceImpl.class);

    @Resource
    PostLikesRelateMapper postLikesRelateMapper;
    @Resource
    PostCommentsRelateMapper postCommentsRelateMapper;

    //gcr add20180523
    @Resource
    ForumPostInfoMapper forumPostInfoMapper;
    @Resource
    AttentionPostMapper attentionPostMapper;
    @Resource
    private AmqpTemplate rabbitTemplate;
    //gcr add end

    /**
     * 帖子新增点赞
     */
    @Transactional
    public void insertPostLike(PostLikesRelateEntity postLikesRelateEntity) throws Exception{

        Long userId = 0L;
        Long postId = 0L;
        try {
            userId = postLikesRelateEntity.getLikeUserId();
            postId = postLikesRelateEntity.getLikeUserId();
            postLikesRelateMapper.insertPostLike(postLikesRelateEntity);
        } catch (Exception e) {

            logger.error("用户{}帖子{}新增点赞异常！入参："+ JSON.toJSONString(postLikesRelateEntity),userId,postId,e);
            throw new Exception("系统繁忙，请稍后重试！");
        }
    }

    /**
     * 帖子取消点赞
     */
    @Transactional
    public int cancelPostToNoLike(PostLikesRelateEntity postLikesRelateEntity) throws Exception{
        Long userId = 0L;
        Long postId = 0L;
        int result = 0;
        try {
            userId = postLikesRelateEntity.getLikeUserId();
            postId = postLikesRelateEntity.getPostId();
            result = postLikesRelateMapper.cancelPostToNoLike(postLikesRelateEntity);
        } catch (Exception e) {
            logger.error("帖子：{} 取消用户：{} 点赞异常！入参："+ JSON.toJSONString(postLikesRelateEntity),postId,userId,e);
        }
        return  result;
    }

    /**
     * 帖子新增评论
     */
    @Transactional
    public void insertPostComment(PostCommentsRelateEntity postCommentsRelateEntity) throws Exception{

        Long userId = 0L;
        Long postId = 0L;
        try {
            userId = postCommentsRelateEntity.getCommentUserId();
            postId = postCommentsRelateEntity.getPostId();
            postCommentsRelateMapper.insertPostComment(postCommentsRelateEntity);
        } catch (Exception e) {
            logger.error("帖子{}新增用户{} 评论异常！入参："+ JSON.toJSONString(postCommentsRelateEntity),postId,userId,e);
            throw new Exception("系统繁忙，请稍后重试！");
        }
    }

    /**
     * 删除帖子评论
     */
    @Transactional
    public int deletePostComment(PostCommentsRelateEntity postCommentsRelateEntity) throws Exception{

        Long userId = 0L;
        Long postId = 0L;
        int result = 0;
        try {
            userId = postCommentsRelateEntity.getCommentUserId();
            postId = postCommentsRelateEntity.getPostId();
            result = postCommentsRelateMapper.deletePostComment(postCommentsRelateEntity);
        } catch (Exception e) {
            logger.error("帖子{}删除用户{}评论异常！入参："+ JSON.toJSONString(postCommentsRelateEntity),postId,userId,e);
            throw new Exception("系统繁忙，请稍后重试！");
        }
        return result;
    }

    /**
     * gcr
     * 帖子新增点赞包含MQ发送事务控制
     */
    @Override
    @Transactional
    public void insertPostLikeMq(PostLikesRelateEntity postLikesRelateEntity) throws Exception {
        Long userId = 0L;
        Long postId = 0L;
        //插入点赞列表
        try {
            userId = postLikesRelateEntity.getLikeUserId();
            postId = postLikesRelateEntity.getPostId();

            Map<String,Object> map = new HashMap<String,Object>();
            map.put("postId", postId);
            map.put("likeUserId",userId);
            //根据用户id和帖子id 查询客户在点赞列表中是否存在 不存在 新增 存在 说明之前取消过 进行更新
            List<PostLikesRelateEntity> postLikesRelatelist = postLikesRelateMapper.selectPostLikebypostIdlikeUserId(map);
           if(postLikesRelatelist.size()>0){
               //存在 更新操作
               postLikesRelateMapper.updatePostToLike(postLikesRelateEntity);

           }else{
               postLikesRelateMapper.insertPostLike(postLikesRelateEntity);
           }


        } catch (Exception e) {

            logger.error("用户{}帖子{}新增点赞异常！入参："+ JSON.toJSONString(postLikesRelateEntity),userId,postId,e);
            throw new Exception("系统繁忙，请稍后重试！");
        }
        //点赞消息数据处理，待发送
        MyMessageDetailEntity myMessageDetailEntity = copyproperty(postLikesRelateEntity);


        //点赞的同时 先查该帖子在帖子关注表中是否有其他关注人 后插入数据
        //关注消息相关联的数据处理 封装成list
        List<MyMessageDetailEntity> list =new ArrayList<MyMessageDetailEntity>();
        //查询关注状态是有效的
        List<AttentionPostEntity> attentionPostList = attentionPostMapper.selectAttentionpostInfoByPostId(postLikesRelateEntity.getPostId());
        if(attentionPostList.size()>0){
            //涉及有其他人点赞和自身点赞
            for(AttentionPostEntity attentionPostEntity:attentionPostList){
                MyMessageDetailEntity entity=new MyMessageDetailEntity();
                BeanUtils.copyProperties(myMessageDetailEntity,entity);
                //如果自身已经关注了  就不用给自身推送点赞了
                if(attentionPostEntity.getUserId().equals(userId)){
                    continue;
                }
                entity.setAttentuserId(attentionPostEntity.getUserId());
                entity.setMsgId(new Long(30));
                list.add(entity);
            }

            //查询关注帖子列表 （例如在点赞之前 先评论已经进行了关注，说明已经存在，不插入数据）
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("userId", userId);
            map.put("postId",postId);
            List<AttentionPostEntity> attentionPostEntities = attentionPostMapper.selectAttentionpostByUserIdandPostId(map);
            //单笔记录 说明已存在
            if(attentionPostEntities.size()>0){

                //原来已关注，取消了，继续关注
                if(attentionPostEntities.get(0).getAttentionStatus()==0){

                    AttentionPostEntity entity=new AttentionPostEntity();
                    entity.setModifyTime(new Date());
                    entity.setAttentionStatus(new Long(1));
                    entity.setUserId(userId);
                    entity.setPostId(postId);
                    try {
                        attentionPostMapper.updateAttentionPost(entity);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new Exception("系统更新出现错误，请稍后重试！");
                    }

                }

                if(list.size()>0){
                    rabbitTemplate.convertAndSend("MessgeExchange", "addLikerelate",JSON.toJSONString(list));
                }

            }else{
                //向关注表中插入自身关注数据
                AttentionPostEntity attentionEntity=new AttentionPostEntity();
                attentionEntity.setUserId(userId);  //当前的用户id
                attentionEntity.setPostId(postId);//帖子id
                attentionEntity.setAttentionStatus(new Long(1));//关注状态 1关注 0取消
                attentionEntity.setModifyTime(myMessageDetailEntity.getCreateTime());
                int i=0;
                i =attentionPostMapper.insertAttentionPost(attentionEntity);
                if(i>0){
                    // 插入成功
                    rabbitTemplate.convertAndSend("MessgeExchange", "addLikerelate",JSON.toJSONString(list));
                }else{
                    throw new RuntimeException("异常事务回滚，不发消息");
                }
            }


        }else{

            //没有有效的数据 （表中可能自身存在关注，但取消了，这种情况要进行更新操作）
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("userId", userId);
            map.put("postId",postId);
            List<AttentionPostEntity> attentionPostEntities = attentionPostMapper.selectAttentionpostByUserIdandPostId(map);
            if(attentionPostEntities.size()>0) {

                //原来已关注，取消了，继续关注
                if (attentionPostEntities.get(0).getAttentionStatus() == 0) {

                    AttentionPostEntity entity = new AttentionPostEntity();
                    entity.setModifyTime(new Date());
                    entity.setAttentionStatus(new Long(1));
                    entity.setUserId(userId);
                    entity.setPostId(postId);
                    try {
                        attentionPostMapper.updateAttentionPost(entity);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new Exception("系统更新出现错误，请稍后重试！");
                    }

                }
            }else{
                //向关注表中插入自身关注数据
                AttentionPostEntity attentionEntity=new AttentionPostEntity();
                attentionEntity.setUserId(userId);  //当前的用户id
                attentionEntity.setPostId(postId);//帖子id
                attentionEntity.setAttentionStatus(new Long(1));//关注状态 1关注 0取消
                attentionEntity.setModifyTime(myMessageDetailEntity.getCreateTime());
                int i = attentionPostMapper.insertAttentionPost(attentionEntity);
            }

        }


        rabbitTemplate.convertAndSend("MessgeExchange", "addLike",JSON.toJSONString(myMessageDetailEntity));
    }


    private MyMessageDetailEntity copyproperty(PostLikesRelateEntity postLikesRelateEntity){

//        private Long postId;帖子ID
//        private Long likeUserId;点赞用户id
//        private Long likeStatus;点赞状态（0取消 1正常
//        private String likeUserName;点赞用户名称
//        private String likeTime;点赞时间
        MyMessageDetailEntity entity=new MyMessageDetailEntity();
        entity.setUserId(postLikesRelateEntity.getLikeUserId());//用户ID
        entity.setMsgId(new Long(10));//消息ID
        entity.setReadYn(new Long(0));//是否已读
        entity.setPostId(postLikesRelateEntity.getPostId());//帖子ID
        Long postId = postLikesRelateEntity.getPostId();
        //发帖人id 和前十个字通过帖子id查询帖子表获取
        ForumPostInfoEntity forumPostInfoEntity = forumPostInfoMapper.selectPostInfoByPostId(postId);
        entity.setPostuserId(forumPostInfoEntity.getUserId());//发帖人ID
        entity.setPostuserName(forumPostInfoEntity.getUserName());//发帖人用户姓名
        //entity.setAttentuserId();//关注帖子ID的用户ID
        entity.setPostPrefixContext(forumPostInfoEntity.getPostContent().substring(0,10));
        entity.setCreateTime(new Date());


        /*String createTime = forumPostInfoEntity.getCreateTime();

        DateFormat df = null;
        try {
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date createtime = df.parse(createTime);
            entity.setCreateTime(createtime);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        return entity;
    }



    /**
     * gcr
     * 帖子新增评论 （考虑数据库加带一个是否是邀请评论的标识，作为判断）
     */
    @Transactional
    @ResponseBody
    public void insertPostCommentMq(PostCommentsRelateEntity postCommentsRelateEntity) throws Exception{

        //至少传入参数  评论内容 帖子id 评论人id，评论人名称，邀请评论的标识type----删除人和删除时间占不考虑
        Long userId = 0L;
        Long postId = 0L;
        Long inviteCommentsYn = postCommentsRelateEntity.getInviteCommentsYn();

        try {
            userId = postCommentsRelateEntity.getCommentUserId();
            postId = postCommentsRelateEntity.getPostId();

            postCommentsRelateEntity.setCommentTime(new Date());//评论时间
            postCommentsRelateEntity.setCommentStatus(new Long(1));//评论状态（0无效 1有效 ）
            //等于1 说明是邀请评论
            if(inviteCommentsYn==1){
                postCommentsRelateEntity.setInviteCommentsYn(new Long(1));
                postCommentsRelateMapper.insertPostComment(postCommentsRelateEntity);
            }else {
                postCommentsRelateMapper.insertPostComment(postCommentsRelateEntity);
            }


        } catch (Exception e) {
            logger.error("帖子{}新增用户{} 评论异常！入参："+ JSON.toJSONString(postCommentsRelateEntity),postId,userId,e);
            throw new Exception("系统繁忙，请稍后重试！");
        }

        //邀请评论
        if(inviteCommentsYn==1){
            //待发送消息数据 消息模板msgid=70 邀请评论
            MyMessageDetailEntity myMessageDetailEntity= copyinventpropertyToMessageDtl(postCommentsRelateEntity);
            List<MyMessageDetailEntity> list =new ArrayList<MyMessageDetailEntity>();
            //查询关注状态是有效的
            List<AttentionPostEntity> attentionPostList = attentionPostMapper.selectAttentionpostInfoByPostId(postCommentsRelateEntity.getPostId());
            if(attentionPostList.size()>0) {
                //涉及有其他人评论和自身评论
                for (AttentionPostEntity attentionPostEntity : attentionPostList) {
                    MyMessageDetailEntity entity = new MyMessageDetailEntity();
                    BeanUtils.copyProperties(myMessageDetailEntity, entity);
                    //如果自身已经关注了  就不用给自身推送了
                    if (attentionPostEntity.getUserId().equals(userId)) {
                        continue;
                    }
                    entity.setAttentuserId(attentionPostEntity.getUserId());
                    entity.setMsgId(new Long(50));
                    list.add(entity);
                }

            }
            rabbitTemplate.convertAndSend("MessgeExchange", "addinvitecomment",JSON.toJSONString(myMessageDetailEntity));
            if(list.size()>0){
                rabbitTemplate.convertAndSend("MessgeExchange", "addinvitecommentrelate",JSON.toJSONString(list));
            }

        }
        else{
            //待发送消息数据 消息模板msgid=20 关于评论
            MyMessageDetailEntity myMessageDetailEntity= copypropertyToMessageDtl(postCommentsRelateEntity);


            //查询关注表
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("userId", userId);
            map.put("postId",postId);
            List<AttentionPostEntity> attentionPostEntities = attentionPostMapper.selectAttentionpostByUserIdandPostId(map);
            if(attentionPostEntities.size()>0) {

                //原来已关注，取消了，继续关注
                if (attentionPostEntities.get(0).getAttentionStatus() == 0) {
                    AttentionPostEntity entity = new AttentionPostEntity();
                    entity.setModifyTime(new Date());
                    entity.setAttentionStatus(new Long(1));
                    entity.setUserId(userId);
                    entity.setPostId(postId);
                    try {
                        attentionPostMapper.updateAttentionPost(entity);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new Exception("系统更新出现错误，请稍后重试！");
                    }
                }
            }else{
                //向关注表中插入自身关注数据
                AttentionPostEntity attentionEntity=new AttentionPostEntity();
                attentionEntity.setUserId(userId);  //当前的用户id
                attentionEntity.setPostId(postId);//帖子id
                attentionEntity.setAttentionStatus(new Long(1));//关注状态 1关注 0取消
                attentionEntity.setModifyTime(myMessageDetailEntity.getCreateTime());
                int i = attentionPostMapper.insertAttentionPost(attentionEntity);
            }


            List<MyMessageDetailEntity> list =new ArrayList<MyMessageDetailEntity>();
            //查询关注状态是有效的
            List<AttentionPostEntity> attentionPostList = attentionPostMapper.selectAttentionpostInfoByPostId(postCommentsRelateEntity.getPostId());
            if(attentionPostList.size()>0) {
                //涉及有其他人评论和自身评论
                for (AttentionPostEntity attentionPostEntity : attentionPostList) {
                    MyMessageDetailEntity entity = new MyMessageDetailEntity();
                    BeanUtils.copyProperties(myMessageDetailEntity, entity);
                    //如果自身已经关注了  就不用给自身推送点赞了
                    if (attentionPostEntity.getUserId().equals(userId)) {
                        continue;
                    }
                    entity.setAttentuserId(attentionPostEntity.getUserId());
                    entity.setMsgId(new Long(40));
                    list.add(entity);
                }

            }

            System.out.println(JSON.toJSONString(myMessageDetailEntity));
            System.out.println(JSON.toJSONString(list));
            rabbitTemplate.convertAndSend("MessgeExchange", "addcomment",JSON.toJSONString(myMessageDetailEntity));
            if(list.size()>0){
                rabbitTemplate.convertAndSend("MessgeExchange", "addcommentrelate",JSON.toJSONString(list));
            }

        }





    }

    private MyMessageDetailEntity copyinventpropertyToMessageDtl(PostCommentsRelateEntity postCommentsRelateEntity){

        MyMessageDetailEntity entity=new MyMessageDetailEntity();

        entity.setUserId(postCommentsRelateEntity.getCommentUserId());//用户ID=评论人id
        entity.setMsgId(new Long(70));//消息ID
        entity.setReadYn(new Long(0));//是否已读
        entity.setPostId(postCommentsRelateEntity.getPostId());//帖子ID
        Long postId = postCommentsRelateEntity.getPostId();
        //发帖人id 和前十个字通过帖子id查询帖子表获取
        ForumPostInfoEntity forumPostInfoEntity = forumPostInfoMapper.selectPostInfoByPostId(postId);
        entity.setPostuserId(forumPostInfoEntity.getUserId());//发帖人ID
        entity.setPostuserName(forumPostInfoEntity.getUserName());//发帖人用户姓名
        //entity.setAttentuserId();//关注帖子ID的用户ID
        entity.setPostPrefixContext(forumPostInfoEntity.getPostContent().substring(0,10));
        entity.setCreateTime(postCommentsRelateEntity.getCommentTime());
        return entity;
    }

    private MyMessageDetailEntity copypropertyToMessageDtl(PostCommentsRelateEntity postCommentsRelateEntity){

        MyMessageDetailEntity entity=new MyMessageDetailEntity();
//        private Long postId;帖子ID
//        private Long commentUserId;评论人ID
//        private String commentUserName;评论人名称
//        private String commentContent;评论内容
//        private Long commentStatus;评论状态（0无效 1有效 ）
//        private Date commentTime;评论时间
//        private Long deleteUser;删除人
//        private Date deleteTime;删除时间

        entity.setUserId(postCommentsRelateEntity.getCommentUserId());//用户ID=评论人id
        entity.setMsgId(new Long(20));//消息ID
        entity.setReadYn(new Long(0));//是否已读
        entity.setPostId(postCommentsRelateEntity.getPostId());//帖子ID
        Long postId = postCommentsRelateEntity.getPostId();
        //发帖人id 和前十个字通过帖子id查询帖子表获取
        ForumPostInfoEntity forumPostInfoEntity = forumPostInfoMapper.selectPostInfoByPostId(postId);
        entity.setPostuserId(forumPostInfoEntity.getUserId());//发帖人ID
        entity.setPostuserName(forumPostInfoEntity.getUserName());//发帖人用户姓名
        //entity.setAttentuserId();//关注帖子ID的用户ID
        entity.setPostPrefixContext(forumPostInfoEntity.getPostContent().substring(0,10));
        entity.setCreateTime(postCommentsRelateEntity.getCommentTime());
        return entity;
    }

}
