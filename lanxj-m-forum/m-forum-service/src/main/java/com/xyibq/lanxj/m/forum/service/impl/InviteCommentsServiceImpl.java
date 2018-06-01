package com.xyibq.lanxj.m.forum.service.impl;

import com.github.pagehelper.PageHelper;
import com.xyibq.lanxj.m.forum.domain.vo.InviteCommentsVo;
import com.xyibq.lanxj.m.forum.mapper.InviteCommentsMapper;
import com.xyibq.lanxj.m.forum.service.InviteCommentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class InviteCommentsServiceImpl implements InviteCommentsService{

    private static final Logger logger = LoggerFactory.getLogger(InviteCommentsServiceImpl.class);

    @Resource
    InviteCommentsMapper inviteCommentsMapper;



    @Override
    public List<InviteCommentsVo> queryInviteCommentsByUserId(String userId) throws Exception {
        List<InviteCommentsVo> inviteCommentsVos=null;
        try{
        inviteCommentsVos = inviteCommentsMapper.selectInviteCommentsByUserId(userId);
    }catch (Exception e) {
        logger.error("查询:{} 邀请列表信息异常！",e);
        throw new Exception("系统繁忙，请稍后重试！");
    }
        return inviteCommentsVos;
    }

    @Override
    public List<InviteCommentsVo> queryInviteCommentsByinviteauth(String invitecommentauth) throws Exception {
        List<InviteCommentsVo> inviteCommentsVoslist=null;

        try{
        inviteCommentsVoslist = inviteCommentsMapper.selectInviteCommentsByinviteauth(invitecommentauth);
        }catch (Exception e) {
            logger.error("查询:{} 邀请有权限用户信息列表信息异常！",e);
            throw new Exception("系统繁忙，请稍后重试！");
        }

        return inviteCommentsVoslist;
    }

    @Override
    public List<InviteCommentsVo> queryInviteCommentsByMap(Map<String, Object> querymap) throws Exception {
        List<InviteCommentsVo> inviteCommentsVoslist=null;

        try{
            inviteCommentsVoslist = inviteCommentsMapper.selectInviteCommentsByMap(querymap);

        }catch (Exception e) {
            logger.error("查询:{} 邀请有权限用户信息列表信息异常！",e);
            throw new Exception("系统繁忙，请稍后重试！");
        }

        return inviteCommentsVoslist;
    }
}
