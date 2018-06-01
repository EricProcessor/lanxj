package com.xyibq.lanxj.m.forum.mapper;

import com.xyibq.lanxj.m.forum.domain.entity.PostLikesRelateEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface PostLikesRelateMapper {

    /**
     * 帖子新增点赞
     */
    public void insertPostLike(PostLikesRelateEntity postLikesRelateEntity);

    /**
     * 帖子取消点赞
     */
    public int cancelPostToNoLike(PostLikesRelateEntity postLikesRelateEntity);

    /**
     * 帖子点赞列表查询
     */
    public List<PostLikesRelateEntity> selectPostLikeList(@Param(value="postId") Long postId);

    /**
     * 帖子点赞列表查询通过帖子id和用户id 查询唯一记录
     */
    public List<PostLikesRelateEntity> selectPostLikebypostIdlikeUserId(Map<String,Object> map);

    /**
     * 帖子更新点赞
     */
    public int updatePostToLike(PostLikesRelateEntity postLikesRelateEntity);


}
