package com.xyibq.lanxj.admin.forum.service;

import com.xyibq.lanxj.admin.forum.domain.entity.ForumTopicInfoEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ForumTopicInfoService {

    /**
     * 查询所有版块列表
     */
    public List<ForumTopicInfoEntity> queryAllForumTopicList();

    /**
     * 新增版块信息
     */
    public void addTopicInfo(ForumTopicInfoEntity forumTopicInfoEntity);

    /**
     * 修改版块信息
     */
    public void updateForumTopicInfo(ForumTopicInfoEntity forumTopicInfoEntity);

    /**
     *  版块发帖审核开关
     */
    public void topicCheckPostSwitch(int topicId,int checkYn);

    /**
     *  匿名发贴版块开关
     */
    public void topicAnonymitySwitch(int topicId,int anonymityYn);

    /**
     * 版块开关
     */
    public void topicStatusSwitch(int topicId,int topicStatus);
}
