package com.xyibq.lanxj.m.forum.service;

import com.xyibq.lanxj.m.forum.domain.vo.MyMessageListVo;

import java.util.List;

public interface MyMessageListService {


    /**
     * 查询我的消息列表，返回前端界面vo涉及到我的消息表和消息表详情中的是否已读状态
     */
    public List<MyMessageListVo> queryMymessageList() throws Exception;




}
