package com.xyibq.lanxj.admin.forum.service.impl;

import com.xyibq.lanxj.admin.forum.domain.entity.ForumTopicInfoEntity;
import com.xyibq.lanxj.admin.forum.mapper.ForumTopicInfoMapper;
import com.xyibq.lanxj.admin.forum.service.ForumTopicInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ForumTopicInfoServiceImpl implements ForumTopicInfoService {

    @Resource
    ForumTopicInfoMapper forumTopicInfoMapper;

    /**
     * 查询系统所有版块列表
     */
    public List<ForumTopicInfoEntity> queryAllForumTopicList(){
        List<ForumTopicInfoEntity> topiclist = forumTopicInfoMapper.selectAllForumTopicList();
        return topiclist;
    }

    /**
     * 新增版块信息
     */
    @Transactional
    public void addTopicInfo(ForumTopicInfoEntity forumTopicInfoEntity){
        forumTopicInfoMapper.insertTopicInfo(forumTopicInfoEntity);
    }

    /**
     * 修改版块信息
     */
    @Transactional
    public void updateForumTopicInfo(ForumTopicInfoEntity forumTopicInfoEntity){
        forumTopicInfoMapper.updateForumTopicInfo(forumTopicInfoEntity);
    }

    /**
     *  版块发帖审核开关
     */
    @Transactional
    public void topicCheckPostSwitch(int topicId,int checkYn){
        ForumTopicInfoEntity forumTopicInfoEntity = new ForumTopicInfoEntity();
        forumTopicInfoEntity.setTopicId(topicId);
        forumTopicInfoEntity.setCheckYn(checkYn);
        forumTopicInfoEntity.setModifyUser("1111");
        forumTopicInfoMapper.updateForumTopicInfo(forumTopicInfoEntity);
    }

    /**
     *  匿名发贴版块开关
     */
    @Transactional
    public void topicAnonymitySwitch(int topicId,int anonymityYn){
        ForumTopicInfoEntity forumTopicInfoEntity = new ForumTopicInfoEntity();
        forumTopicInfoEntity.setTopicId(topicId);
        forumTopicInfoEntity.setAnonymityYn(anonymityYn);
        forumTopicInfoEntity.setModifyUser("1111");
        forumTopicInfoMapper.updateForumTopicInfo(forumTopicInfoEntity);
    }

    /**
     * 版块关闭、启用开关
     */
    @Transactional
    public void topicStatusSwitch(int topicId,int topicStatus){
        ForumTopicInfoEntity forumTopicInfoEntity = new ForumTopicInfoEntity();
        forumTopicInfoEntity.setTopicId(topicId);
        forumTopicInfoEntity.setTopicStatus(topicStatus);
        forumTopicInfoEntity.setModifyUser("1111");
        forumTopicInfoMapper.updateForumTopicInfo(forumTopicInfoEntity);
    }
}
