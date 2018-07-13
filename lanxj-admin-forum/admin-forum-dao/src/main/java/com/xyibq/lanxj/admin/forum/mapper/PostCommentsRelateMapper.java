package com.xyibq.lanxj.admin.forum.mapper;

import com.xyibq.lanxj.admin.forum.domain.entity.PostCommentsRelateEntity;
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
    public void insertPostCommentByAdmin(PostCommentsRelateEntity postCommentsRelateEntity);

    /**
     * 用户删除帖子评论
     */
    public int deletePostCommentByAdmin(PostCommentsRelateEntity postCommentsRelateEntity);

    /**
     * 帖子评论列表查询
     */
    public List<PostCommentsRelateEntity> selectPostCommentList(@Param("postId") String postId);

}
