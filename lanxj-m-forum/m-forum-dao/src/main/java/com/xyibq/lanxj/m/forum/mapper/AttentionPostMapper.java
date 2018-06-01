package com.xyibq.lanxj.m.forum.mapper;

import com.xyibq.lanxj.m.forum.domain.entity.AttentionPostEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 关注帖子信息表【attention_post】 mapper
 */
@Mapper
@Component
public interface AttentionPostMapper {


   /**
    * 根据发帖人ID(user_id)和帖子id，查询关注帖子信息列表
    */
   //public List<AttentionPostEntity> selectAttentionpostByUserIdandPostId(String userId,String postId) throws Exception;


   /**
    * 根据发帖人ID(user_id)和帖子id，查询关注帖子信息列表
    */
   public List<AttentionPostEntity> selectAttentionpostByUserIdandPostId(Map<String,Object> map) throws Exception;


   /**
    * 插入关注帖子信息列表
    */
   public int insertAttentionPost(AttentionPostEntity attentionPostEntity);

   /**
    * 更新关注帖子信息列表
    */
   public void updateAttentionPost(AttentionPostEntity attentionPostEntity);

   /**
    * 删除关注帖子信息列表
    */
   public void deleteAttentionPostByPrimaryKey(Long id);

   /**
    * 根据帖子id和关注状态，查询关注帖子的客户信息
    */
   public List<AttentionPostEntity> selectAttentionpostInfoByPostId(Long postId) throws Exception;








}
