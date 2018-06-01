package com.xyibq.lanxj.admin.forum.service.impl;

import com.xyibq.lanxj.admin.forum.common.util.CheckUtil;
import com.xyibq.lanxj.admin.forum.domain.entity.ForumPostInfoEntity;
import com.xyibq.lanxj.admin.forum.domain.vo.ForumPostInfoDetailVo;
import com.xyibq.lanxj.admin.forum.mapper.ForumPostInfoMapper;
import com.xyibq.lanxj.admin.forum.mapper.PostCommentsRelateMapper;
import com.xyibq.lanxj.admin.forum.mapper.PostLikesRelateMapper;
import com.xyibq.lanxj.admin.forum.mapper.PostPicUrlRelateMapper;
import com.xyibq.lanxj.admin.forum.service.ForumPostInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ForumPostInfoServiceImpl implements ForumPostInfoService {


    @Resource
    ForumPostInfoMapper forumPostInfoMapper;
    @Resource
    PostCommentsRelateMapper postCommentsRelateMapper;
    @Resource
    PostLikesRelateMapper postLikesRelateMapper;
    @Resource
    PostPicUrlRelateMapper postPicUrlRelateMapper;


    /**
     * 将帖子置顶
     */
    @Transactional
    public void setPostToTop(String postId){
        forumPostInfoMapper.setPostToTop(postId);
    }

    /**
     * 取消帖子置顶
     */
    @Transactional
    public void cancelPostTop(String postId){
        forumPostInfoMapper.cancelPostTop(postId);
    }

    /**
     * 查询版块帖子列表(不含点赞和评论)
     */
    public List<ForumPostInfoEntity> queryTopicPostList(String topic){
        return forumPostInfoMapper.selectTopicPostList(topic);
    }

    /**
     * 查询帖子详情
     */
    public ForumPostInfoDetailVo queryPostDetail(String postId) throws Exception{

        ForumPostInfoDetailVo forumPostInfoDetailVo = new ForumPostInfoDetailVo();

        ForumPostInfoEntity forumPostInfoEntity = forumPostInfoMapper.selectPostDetail(postId);
        //相同属性赋值
        if(!CheckUtil.checkEmpty(forumPostInfoEntity)){
            BeanUtils.copyProperties(forumPostInfoEntity,forumPostInfoDetailVo);
        }else{
            throw new Exception("抱歉，未查询到该论坛信息！");
        }
        forumPostInfoDetailVo.setPostCommentsRelateList(postCommentsRelateMapper.selectPostCommentList(postId));
        forumPostInfoDetailVo.setPostLikesRelateList(postLikesRelateMapper.selectPostLikeList(postId));
        forumPostInfoDetailVo.setPostPicUrlRelateList(postPicUrlRelateMapper.selectPostPicUrlList(postId));

        return forumPostInfoDetailVo;
    }

    /**
     * 删除帖子
     */
    @Transactional
    public void removePostInfoByAdmin(String postId){
        forumPostInfoMapper.deletePostInfo(postId);
    }

    /**
     * 查询帖子置顶日期  截至到当日的帖子
     */
    public List<String> queryTodayWillCancelPostList(){
        return forumPostInfoMapper.selectTodayWillCancelPostList();
    }
}
