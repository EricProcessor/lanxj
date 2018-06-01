package com.xyibq.lanxj.m.forum.service;

import com.xyibq.lanxj.m.forum.domain.entity.AttentionPostEntity;

import java.util.List;

public interface AttentionPostService {


   /**
    * 查询关注帖子信息
    */
   //public List<AttentionPostEntity> queryAttentionpostbyuseridandpostid(Map<String,Object> map) throws Exception;

   /**
    * 查询关注帖子信息
    */
   public List<AttentionPostEntity> queryAttentionpostbyuseridandpostid(String userid, String postid) throws Exception;

   /**
    * 新增关注帖子信息
    */
   public int addAttentionPostInfo(AttentionPostEntity attentionPostEntity)throws Exception;


   /**
    * 更新关注帖子信息
    */
   public int updateAttentionPostInfo(AttentionPostEntity attentionPostEntity)throws Exception;


   /**
    * 删除关注帖子信息
    */
   //public int delAttentionPostInfo(String userid,String postid)throws Exception;

   /**
    * 删除关注帖子信息
    */
   public int delAttentionPostByPrimaryKey(Long id)throws Exception;


}
