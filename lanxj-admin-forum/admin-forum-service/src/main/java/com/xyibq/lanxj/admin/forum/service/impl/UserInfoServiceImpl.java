package com.xyibq.lanxj.admin.forum.service.impl;


import com.xyibq.lanxj.admin.forum.common.util.DateUtil;
import com.xyibq.lanxj.admin.forum.common.util.ListSortUtil;
import com.xyibq.lanxj.admin.forum.domain.entity.ForumPostInfoEntity;
import com.xyibq.lanxj.admin.forum.domain.entity.PostCommentsRelateEntity;
import com.xyibq.lanxj.admin.forum.domain.entity.UserInfoEntity;
import com.xyibq.lanxj.admin.forum.domain.vo.UserTalentVo;
import com.xyibq.lanxj.admin.forum.mapper.ForumPostInfoMapper;
import com.xyibq.lanxj.admin.forum.mapper.PostCommentsRelateMapper;
import com.xyibq.lanxj.admin.forum.mapper.UserInfoMapper;
import com.xyibq.lanxj.admin.forum.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserInfoServiceImpl implements UserInfoService {


    private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Resource
    UserInfoMapper userInfoMapper;

    @Resource
    ForumPostInfoMapper forumPostInfoMapper;

    @Resource
    PostCommentsRelateMapper postCommentsRelateMapper;



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

    /**
     * 用户达人榜单查询接口
     *
     */
    @Override
    public List<UserTalentVo> UserTalentList(String userId) throws Exception {

        //1 查询全部用户数据
        List<UserInfoEntity> userInfolist = userInfoMapper.UserInfoList();

        List<UserTalentVo> userTalentVolsit = new ArrayList<UserTalentVo>();
        //2 查询每个用户的发帖数 评论数 帖子回复数 被赞数
        //hashmap 每个id对应key  整理一张实体表list
        Map<String,UserTalentVo> map=new HashMap<String,UserTalentVo>();
        for(UserInfoEntity userInfoEntity:userInfolist){
            if(userInfoEntity!=null){
                UserTalentVo userTalentVo=new UserTalentVo();
                userTalentVo.setUserId(userInfoEntity.getUserId());
                userTalentVo.setUserName(userInfoEntity.getUserName());
                //userTalentVo.setImageUrl(userInfoEntity.getUrl());
                Long userId1 = userInfoEntity.getUserId();
                String UserId=userId1.toString();

                map.put(UserId,userTalentVo);
            }
        }
        //3 实体表中计算 4个维度相加结果最多的五个人信息

        System.out.println("\n通过Map.entrySet使用iterator遍历key和value: ");
        Iterator map1it=map.entrySet().iterator();
        while(map1it.hasNext())
        {
            Map.Entry<String,UserTalentVo> entry=(Map.Entry<String, UserTalentVo>) map1it.next();
            System.out.println("Key: "+entry.getKey());
            System.out.println("value: "+entry.getValue().toString());
           // System.out.println("Key: "+entry.getKey()+" Value: "+entry.getValue());
            //UserTalentVo vo=entry.getValue();
            UserTalentVo vo = entry.getValue();

            Map<String,Object> haspmap = new HashMap<String,Object>();
            String firstDay = DateUtil.getcurrentDatefirstDate();
            String lastDay = DateUtil.getcurrentDatelastDate();
            haspmap.put("firstDay", firstDay);
            haspmap.put("lastDay", lastDay);
            haspmap.put("userId",entry.getKey());

            //计算发帖数

            //int i = userInfoMapper.UserSendPostcount(entry.getKey());
            int i = userInfoMapper.UserSendCommentcountbycurrentdate(haspmap);

            //计算评论数
            //int j = userInfoMapper.UserSendCommentcount(entry.getKey());
            int j = userInfoMapper.UserSendCommentcountbycurrentdate(haspmap);

            //计算被赞数
            //int k = userInfoMapper.UserPostlikecount(entry.getKey());
            int k = userInfoMapper.UserPostlikecountbycurrentdate(haspmap);
            //计算回复数  先查询所有帖子

            int m=0;
            List<ForumPostInfoEntity> forumPostInfolist = forumPostInfoMapper.selectPostListbyuserIdandDate(haspmap);
             if(forumPostInfolist.size()>0){
                 for (ForumPostInfoEntity forumPostInfoEntity:forumPostInfolist){
                     //根据帖子postid查询其关联的评论
                     Long postId = forumPostInfoEntity.getPostId();
                     List<PostCommentsRelateEntity> postCommentsRelatelist = postCommentsRelateMapper.selectPostCommentList(postId.toString());
                     if(forumPostInfolist.size()>0){
                         m=m+forumPostInfolist.size();
                     }
                 }
             }

             int totalcount=i+j+k+m;

             // 计算总和
            vo.setPostCount(i);//发帖数量
            vo.setAnswerCount(m);//回复数量(帖子回复数量)
            vo.setAnswerlikesCount(k);//被赞数
            vo.setCommentCount(j);//评论数
            vo.setTotalCount(totalcount);//总和
            userTalentVolsit.add(vo);

        }
        //汇总最大排序
        System.out.println(userTalentVolsit.size());
        ListSortUtil<UserTalentVo> sortList = new ListSortUtil<UserTalentVo>();
        // sortList.sort(targetList, "hits", "asc");
        sortList.sort(userTalentVolsit, "totalCount", "desc");
        System.out.println("排序后：" +userTalentVolsit.toString());

        //取前五个
        List<UserTalentVo> userTalentVolist = new ArrayList<UserTalentVo>();
        // list1 = targetList.subList(start, end); start,end分别是第几个到第几个。
        if(userTalentVolsit.size()<5){
            userTalentVolist = userTalentVolsit.subList(0, userTalentVolsit.size());
        }else{
            userTalentVolist = userTalentVolsit.subList(0, 5);
        }

        System.out.println("排序后111：" +userTalentVolist);
        return userTalentVolist;
    }
}
