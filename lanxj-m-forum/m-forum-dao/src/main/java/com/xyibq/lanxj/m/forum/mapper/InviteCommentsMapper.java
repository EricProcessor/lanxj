package com.xyibq.lanxj.m.forum.mapper;

import com.xyibq.lanxj.m.forum.domain.vo.InviteCommentsVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 邀请评论表【user_info】 mapper
 */
@Mapper
@Component
public interface InviteCommentsMapper {


 /**
  * 根据发帖人ID(user_id) 查询用户信息列表， 查看是否有邀请评论权限(0无 1有)
  */
 public List<InviteCommentsVo> selectInviteCommentsByUserId(String userId) throws Exception;

 /**
  * 根据邀请评论权限(invite_comment_auth) 查询用户信息列表
  */
 public List<InviteCommentsVo> selectInviteCommentsByinviteauth(String invitecommentauth) throws Exception;


 /**
  * 根据邀请评论权限(invite_comment_auth,userid) 查询用户信息列表(排除自身)
  */
 public List<InviteCommentsVo> selectInviteCommentsByMap(Map<String, Object> querymap) throws Exception;


}
