package com.xyibq.lanxj.m.forum.service;

import com.xyibq.lanxj.m.forum.domain.vo.InviteCommentsVo;

import java.util.List;
import java.util.Map;

public interface InviteCommentsService {


    /**
     * 根据发帖人ID(user_id) 查询用户信息列表， 查看是否有邀请评论权限(0无 1有)
     */
    public List<InviteCommentsVo> queryInviteCommentsByUserId(String userId) throws Exception;


    /**
     * 根据邀请评论权限(invite_comment_auth) 查询用户信息列表
     */
    public List<InviteCommentsVo> queryInviteCommentsByinviteauth(String invitecommentauth) throws Exception;

    /**
     * 根据邀请评论权限(invite_comment_auth,userid) 查询用户信息列表(不包含自身的查询 sql处理)
     */
    public List<InviteCommentsVo> queryInviteCommentsByMap(Map<String, Object> querymap) throws Exception;
}
