package com.xyibq.lanxj.admin.forum.service;


import com.xyibq.lanxj.admin.forum.domain.entity.MyMessageDetailEntity;

import java.util.List;

public interface MyMessageDetailService {


    /**
     * 查询我的消息详情列表
     * @param userId
     * @param msg_id
     */
    public List<MyMessageDetailEntity> queryMessageDetailList(Long msg_id, String userId) throws Exception;


    /**
     * 查询我的消息详情列表
     * @param userId
     * @param msg_id
     */
    public List<MyMessageDetailEntity> queryMessageDetailAllList(Long msg_id, String userId) throws Exception;


    /**
     * 查询我的消息未读列表
     * @param userId
     */
    public List<MyMessageDetailEntity> queryUnReadmessageList(Long userId) throws Exception;


    /**
     * 查询我的消息未读数量 （包含所有消息类型ID）
     * @param userId
     */
    public int queryUnReadMesscountbyuserid(Long userId) throws Exception;



    /**
     * 查询我的消息详情列表
     * @param entity
     */
    public List<MyMessageDetailEntity> queryMymessageDetaillist(MyMessageDetailEntity entity) throws Exception;


    /**
     * 更新用户信息
     */
    public int modifyMyMessageDetailInfo(MyMessageDetailEntity entity)throws Exception;

    /**
     * 新增用户信息
     */
    public int addMessageDetailInfo(MyMessageDetailEntity entity)throws Exception;



}
