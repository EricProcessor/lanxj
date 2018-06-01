package com.xyibq.lanxj.admin.forum.service;


import com.xyibq.lanxj.admin.forum.domain.entity.UserInfoEntity;

import java.util.List;

public interface UserInfoService {


    /**
     * 根据条件查询用户信息
     */
    public List<UserInfoEntity> queryUser(UserInfoEntity userInfoEntity) throws Exception;


    /**
     * 发帖人ID查询用户信息
     */
    public List<UserInfoEntity> queryUserbyuserid(String userid) throws Exception;


    /**
     * 发帖人姓名user_name查询用户信息
     */
    public List<UserInfoEntity> queryUserbyuserName(String userName) throws Exception;


    /**
     * 员工岗位position查询用户信息
     */
    public List<UserInfoEntity> queryUserbyposition(String position) throws Exception;



    /**
     * 新增用户信息
     */
    public int addUserInfo(UserInfoEntity userInfoEntity)throws Exception;


    /**
     * 更新用户信息
     */
    public int modifyUserInfo(UserInfoEntity userInfoEntity)throws Exception;


    /**
     * 删除用户信息
     */
    //public int delUserInfo(String userid,String postid)throws Exception;

    /**
     * 删除用户信息
     */
    public int removeUserByPrimaryKey(Long id)throws Exception;





    /**
     * 当前登录用户的发帖数查询接口
     */
    public int UserSendPostcount(String userid)throws Exception;

    /**
     * 当前登录用户的评论数查询接口
     */
    public int UserSendCommentcount(String userid)throws Exception;

    /**
     * 当前登录用户的帖子被赞数查询接口
     *
     */
    public int UserPostlikecount(String userid)throws Exception;


}
