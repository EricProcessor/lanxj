package com.xyibq.lanxj.m.forum.service;

import com.xyibq.lanxj.m.forum.domain.entity.ForumPostInfoEntity;
import com.xyibq.lanxj.m.forum.domain.vo.ForumPostInfoDetailVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 论坛帖子信息服务
 */
public interface ForumPostInfoService {

    /**
     * 新增帖子信息
     */
    public String addPostInfo(Map postInfoMap)throws Exception;

    /**
     * 查询根据版块置顶帖子
     */
    public List<ForumPostInfoDetailVo> queryTopPostsByTopicId(Map<String,Object> map) throws Exception;

    /**
     * 根据版块ID 查询帖子（未置顶）列表， 如果topicId为空，查询所有帖子
     */
    public List<ForumPostInfoDetailVo> queryNoTopPostsByTopicId(Map<String,Object> map) throws Exception;

    /**
     * 查询帖子详情（包含点赞及评论）
     */
    public ForumPostInfoDetailVo queryPostInfoByPostId(Long postId) throws Exception;

    /**
     * 查询员工动态列表
     */
    public List<ForumPostInfoDetailVo> queryUserDynamicPostList(Map<String,Object> map) throws Exception;

    /**
     * 删除帖子
     */
    public void removePostInfo(Long postId) throws Exception;

}
