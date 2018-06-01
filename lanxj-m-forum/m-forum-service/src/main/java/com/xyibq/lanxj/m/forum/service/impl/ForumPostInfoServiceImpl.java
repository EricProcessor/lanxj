package com.xyibq.lanxj.m.forum.service.impl;

import com.github.pagehelper.PageHelper;
import com.xyibq.lanxj.m.forum.common.redis.RedisService;
import com.xyibq.lanxj.m.forum.common.util.CheckUtil;
import com.xyibq.lanxj.m.forum.common.util.ConstantUtil;
import com.xyibq.lanxj.m.forum.common.util.ResultUtil;
import com.xyibq.lanxj.m.forum.common.util.SensitiveWordUtil;
import com.xyibq.lanxj.m.forum.domain.entity.PostCommentsRelateEntity;
import com.xyibq.lanxj.m.forum.domain.entity.PostPicUrlRelateEntity;
import com.xyibq.lanxj.m.forum.domain.vo.ForumPostInfoDetailVo;
import com.xyibq.lanxj.m.forum.domain.entity.ForumPostInfoEntity;
import com.xyibq.lanxj.m.forum.domain.entity.PostLikesRelateEntity;
import com.xyibq.lanxj.m.forum.mapper.*;
import com.xyibq.lanxj.m.forum.service.ForumPostInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.*;

@Service
public class ForumPostInfoServiceImpl implements ForumPostInfoService {

    private static final Logger logger = LoggerFactory.getLogger(ForumPostInfoServiceImpl.class);


    @Resource
    ForumPostInfoMapper forumPostInfoMapper;
    @Resource
    PostLikesRelateMapper postLikesRelateMapper;
    @Resource
    PostCommentsRelateMapper postCommentsRelateMapper;
    @Resource
    PostPicUrlRelateMapper postPicUrlRelateMapper;

    @Resource
    private RedisService redisService;

    /**
     * 新增帖子信息
     */
    @Transactional
    public String addPostInfo(Map postInfoMap) throws Exception {


        //新增帖子基本信息
        ForumPostInfoEntity forumPostInfoEntity = (ForumPostInfoEntity) postInfoMap.get("forumPostInfoEntity");

        //获取最大post_id
        int maxPostId = forumPostInfoMapper.selectForumPostInfoMaxId();
        Long nextPostId = Long.valueOf(++maxPostId);

        try {
            forumPostInfoEntity.setPostId(nextPostId);
            forumPostInfoMapper.insertPostInfo(forumPostInfoEntity);
        } catch (Exception e) {
            logger.error("新增帖子基本信息异常！", e);
            throw new Exception("新增帖子基本信息异常！");
        }

        //新增帖子关联Url
        try {
            List<PostPicUrlRelateEntity> postPicUrlRelateEntityList = (List<PostPicUrlRelateEntity>) postInfoMap.get("postPicUrlRelateEntityList");
            if (null != postPicUrlRelateEntityList) {
                for (PostPicUrlRelateEntity urlRelateEntity : postPicUrlRelateEntityList) {
                    urlRelateEntity.setPostId(nextPostId);
                }
            }
            postPicUrlRelateMapper.batchInsertPostPicUrl(postPicUrlRelateEntityList);
        } catch (Exception e) {
            logger.error("新增帖子图片URL异常！", e);
            throw new Exception("新增帖子图片URL异常！");
        }

        return  ResultUtil.successMsg();
    }

    /**
     * 根据版块ID 查询置顶帖子
     */
    public List<ForumPostInfoDetailVo> queryTopPostsByTopicId(Map<String,Object> map) throws Exception{

        Long topicId = Long.valueOf(map.get("topicId").toString());
        Integer pageSize = (Integer)map.get("pageSize");
        Integer pageNum = (Integer)map.get("pageNum");
        List<ForumPostInfoDetailVo> topPostDetailVoList = new ArrayList<ForumPostInfoDetailVo>();

        //拼接查询实体
        ForumPostInfoEntity forumPostInfoEntity = new ForumPostInfoEntity();
        forumPostInfoEntity.setTopicId(topicId);
        forumPostInfoEntity.setTopYn(ConstantUtil.POST_INFO_TOP_YES);
        forumPostInfoEntity.setPostStatus(ConstantUtil.POST_STATUS_NORMAL);

        //page分页
        PageHelper.startPage(pageNum,pageSize);
        List<ForumPostInfoEntity> topPostlist=  forumPostInfoMapper.selectPostListByTopicId(forumPostInfoEntity);

        if(null != topPostlist){
            for(ForumPostInfoEntity postInfo : topPostlist){
                topPostDetailVoList.add(this.queryPostInfoByPostId(postInfo.getPostId()));
            }
        }

        return topPostDetailVoList;
    }

    /**
     * 根据版块ID 查询未置顶 帖子列表
     */
    public List<ForumPostInfoDetailVo> queryNoTopPostsByTopicId(Map<String,Object> map) throws Exception{

        Long topicId = Long.valueOf(map.get("topicId").toString());
        Integer pageSize = (Integer)map.get("pageSize");
        Integer pageNum = (Integer)map.get("pageNum");

        List<ForumPostInfoDetailVo> noTopPostDetailVoList = new ArrayList<ForumPostInfoDetailVo>();

        ForumPostInfoEntity forumPostInfoEntity = new ForumPostInfoEntity();
        forumPostInfoEntity.setTopicId(topicId);
        forumPostInfoEntity.setTopYn(ConstantUtil.POST_INFO_TOP_NO);
        forumPostInfoEntity.setPostStatus(ConstantUtil.POST_STATUS_NORMAL);

        //page分页
        PageHelper.startPage(pageNum,pageSize);
        List<ForumPostInfoEntity> noTopPostlist=  forumPostInfoMapper.selectPostListByTopicId(forumPostInfoEntity);

        if(null != noTopPostlist){
            for(ForumPostInfoEntity postInfo : noTopPostlist){
                noTopPostDetailVoList.add(this.queryPostInfoByPostId(postInfo.getPostId()));
            }
        }

        return noTopPostDetailVoList;
    }

    /**
     * 查询帖子详情（包含点赞及评论 列表）
     */
    public ForumPostInfoDetailVo queryPostInfoByPostId(Long postId)throws Exception{

        ForumPostInfoDetailVo forumPostInfoDetailVo = new ForumPostInfoDetailVo();

        //查询帖子点赞列表
        List<PostLikesRelateEntity> likesRelateList = postLikesRelateMapper.selectPostLikeList(postId);
        //查询帖子评论列表
        List<PostCommentsRelateEntity> commentsRelates = postCommentsRelateMapper.selectPostCommentList(postId);
        //查询帖子图片url
        List<PostPicUrlRelateEntity> postPicUrlList = postPicUrlRelateMapper.selectPostPicUrlList(postId);
        //查询帖子基本信息
        ForumPostInfoEntity forumPostInfoEntity = forumPostInfoMapper.selectPostInfoByPostId(postId);

        //相同属性赋值
        if(!CheckUtil.checkEmpty(forumPostInfoEntity)){
            BeanUtils.copyProperties(forumPostInfoEntity,forumPostInfoDetailVo);
        }else{
            throw new Exception("抱歉，未查询到该论坛信息！");
        }
        forumPostInfoDetailVo.setPostLikesRelateList(likesRelateList);
        forumPostInfoDetailVo.setPostCommentsRelateList(commentsRelates);
        forumPostInfoDetailVo.setPostPicUrlRelateList(postPicUrlList);

        return forumPostInfoDetailVo;
    }


    /**
     * 查询员工动态列表
     */
    public List<ForumPostInfoDetailVo> queryUserDynamicPostList(Map<String,Object> map) throws Exception{


        List<ForumPostInfoDetailVo> userDynamicPostListInit = new ArrayList<ForumPostInfoDetailVo>();
        Long userId = Long.valueOf(map.get("userId").toString());
        Integer pageSize = (Integer)map.get("pageSize");
        Integer pageNum = (Integer)map.get("pageNum");

        //page分页
        PageHelper.startPage(pageNum,pageSize);
        //查询帖子基本信息
        List<ForumPostInfoEntity> userPostList = forumPostInfoMapper.selectPostListByUserId(userId);

        if(null != userPostList){
            for(ForumPostInfoEntity postInfo : userPostList){
                userDynamicPostListInit.add(this.queryPostInfoByPostId(postInfo.getPostId()));
            }
        }else{
            logger.info(">>>>>> 查询员工："+userId+"动态帖子列表 为空..........");
        }

        return userDynamicPostListInit;
    }


    /**
     * 删除帖子
     */
    @Transactional
    public void removePostInfo(Long postId) throws Exception{
        forumPostInfoMapper.deletePostInfo(postId);
    }


}
