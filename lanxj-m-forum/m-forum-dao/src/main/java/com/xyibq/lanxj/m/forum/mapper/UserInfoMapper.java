package com.xyibq.lanxj.m.forum.mapper;


import com.xyibq.lanxj.m.forum.domain.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserInfoMapper {


    /**
     * 根据条件封装实体类，模糊查询用户信息
     */
    public List<UserInfoEntity> selectUserInfo(UserInfoEntity userInfoEntity);

    /**
     * 发帖人ID查询用户信息
     */
    public List<UserInfoEntity> selectUserbyuserid(String userid);


    /**
     * 发帖人姓名user_name查询用户信息
     */
    public List<UserInfoEntity> selectUserbyuserName(String userName);


    /**
     * 员工岗位position查询用户信息
     */
    public List<UserInfoEntity> selectUserbyposition(String position);


    /**
     * 根据主键删除用户信息
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入用户信息
     */
    int insertUserInfo(UserInfoEntity record);

    /**
     * 根据主键查询用户信息列表
     */
    UserInfoEntity selectByPrimaryKey(Long id);

    /**
     * 更新用户信息列表
     */
    int updateUserInfo(UserInfoEntity record);

    /**
     * 更新用户信息列表byprimarykey
     */
    //int updateByPrimaryKey(UserInfoEntity record);




    /**
     * 当前登录用户的发帖数查询接口
     */
    int UserSendPostcount(String userid);

    /**
     * 当前登录用户的评论数查询接口
     */
    int UserSendCommentcount(String userid);

    /**
     * 当前登录用户的帖子被赞数查询接口
     */
    int UserPostlikecount(String userid);
}