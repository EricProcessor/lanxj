package com.xyibq.lanxj.m.forum.service.impl;

import com.xyibq.lanxj.m.forum.domain.entity.UserInfoEntity;
import com.xyibq.lanxj.m.forum.mapper.UserInfoMapper;
import com.xyibq.lanxj.m.forum.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService{


    private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Resource
    UserInfoMapper userInfoMapper;


    /**
     * 根据条件查询用户信息
     */
    @Override
    public List<UserInfoEntity> queryUser(UserInfoEntity userInfoEntity) throws Exception {

        List<UserInfoEntity> userInfoList = userInfoMapper.selectUserInfo(userInfoEntity);
        return userInfoList;
    }


    /**
     * 发帖人ID（用户id）查询用户信息
     */
    @Override
    public List<UserInfoEntity> queryUserbyuserid(String userid) throws Exception {

        //Long userId=Long.parseLong(userid);
        List<UserInfoEntity> userInfoList = userInfoMapper.selectUserbyuserid(userid);
        return userInfoList;
    }

    /**
     * 发帖人姓名user_name查询用户信息
     */
    @Override
    public List<UserInfoEntity> queryUserbyuserName(String userName) throws Exception {
        List<UserInfoEntity> userInfoList = userInfoMapper.selectUserbyuserName(userName);
        return userInfoList;
    }

    /**
     * 员工岗位position查询用户信息
     */
    @Override
    public List<UserInfoEntity> queryUserbyposition(String position) throws Exception {
        List<UserInfoEntity> userInfoList = userInfoMapper.selectUserbyposition(position);
        return userInfoList;
    }

    /**
     * 新增用户信息
     */
    @Override
    @Transactional
    public int addUserInfo(UserInfoEntity userInfoEntity) throws Exception {
        int i = userInfoMapper.insertUserInfo(userInfoEntity);
        return i;
    }

    /**
     * 更新用户信息
     */
    @Override
    @Transactional
    public int modifyUserInfo(UserInfoEntity userInfoEntity) throws Exception {
        int i = userInfoMapper.updateUserInfo(userInfoEntity);
        return i;
    }

    /**
     * 删除用户信息
     */
    @Override
    @Transactional
    public int removeUserByPrimaryKey(Long id) throws Exception {
        int i = userInfoMapper.deleteByPrimaryKey(id);
        return i;
    }

    /**
     * 当前登录用户的发帖数查询接口
     */
    @Override
    public int UserSendPostcount(String userid) throws Exception {
        String userId=userid;
        int i = userInfoMapper.UserSendPostcount(userId);
        return i;
    }

    /**
     * 当前登录用户的评论数查询接口
     */
    @Override
    public int UserSendCommentcount(String userid) throws Exception {

        String userId=userid;
        int i = userInfoMapper.UserSendCommentcount(userId);
        return i;

    }

    /**
     * 当前登录用户的帖子被赞数查询接口
     */
    @Override
    public int UserPostlikecount(String userid) throws Exception {
        String userId=userid;
        int i = userInfoMapper.UserPostlikecount(userId);
        return i;
    }
}
