package com.xyibq.lanxj.admin.forum.mapper;

import com.xyibq.lanxj.admin.forum.domain.entity.ForumPostInfoEntity;
import com.xyibq.lanxj.admin.forum.domain.entity.ForumTopicInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface ForumPostInfoMapper {

    /**
     * 将帖子置顶
     */
    public void setPostToTop(String postId);

    /**
     * 将帖子置顶
     */
    public void cancelPostTop(String postId);

    /**
     * 查询版块帖子列表
     */
    public List<ForumPostInfoEntity> selectTopicPostList(String topic);

    /**
     * 查询帖子详情（包含点赞及评论）
     */
    public ForumPostInfoEntity selectPostDetail(@Param("postId")String postId);

    /**
     * 删除帖子
     */
    public void deletePostInfo(@Param("postId")String postId);

    /**
     * 查询置顶日期截至到今天的帖子
     */
    public List<String> selectTodayWillCancelPostList();

    /**
     * 发帖 拒绝帖子30 post_status帖子状态（0草稿 10待审 20正常 30拒绝 40删除）
     */
    public int updatePostInfo(@Param("postId")String postId);


    /**
     * 数据统计——版块评论前五名
     */
    public List<ForumTopicInfoEntity> selectTopCommentCount(String topicId);

    /**
     * 数据统计——版块点赞前五名
     */
    public List<ForumTopicInfoEntity> selectTopLikeCount(String topicId);

    /**
     * 数据统计——版块评论前五名
     */
    public List<ForumTopicInfoEntity> selectTopPVCount(String topicId);



    /**
     * 根据发帖人id和当前月份的第一天和最后一天，查询其所发的所有帖子
     */
    public List<ForumPostInfoEntity> selectPostListbyuserIdandDate(Map<String, Object> map);

}
