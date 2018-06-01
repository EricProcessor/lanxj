package com.xyibq.lanxj.m.forum.mapper;

import com.xyibq.lanxj.m.forum.domain.entity.PostCommentsRelateEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PostCommentsRelateMapper {

    /**
     * 帖子新增评论
     */
    public void insertPostComment(PostCommentsRelateEntity postCommentsRelateEntity);

    /**
     * 用户删除帖子评论
     */
    public int deletePostComment(PostCommentsRelateEntity postCommentsRelateEntity);

    /**
     * 帖子评论列表查询
     */
    public List<PostCommentsRelateEntity> selectPostCommentList(@Param(value="postId") Long postId);

}
