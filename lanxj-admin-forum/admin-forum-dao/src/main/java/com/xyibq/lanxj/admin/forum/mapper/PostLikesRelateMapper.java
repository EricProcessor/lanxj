package com.xyibq.lanxj.admin.forum.mapper;

import com.xyibq.lanxj.admin.forum.domain.entity.PostLikesRelateEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PostLikesRelateMapper {

    /**
     * 帖子点赞列表查询
     */
    public List<PostLikesRelateEntity> selectPostLikeList(@Param("postId") String postId);

}
