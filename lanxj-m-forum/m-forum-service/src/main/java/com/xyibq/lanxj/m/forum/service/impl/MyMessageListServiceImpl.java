package com.xyibq.lanxj.m.forum.service.impl;

import com.xyibq.lanxj.m.forum.domain.vo.MyMessageListVo;
import com.xyibq.lanxj.m.forum.mapper.MyMessageListMapper;
import com.xyibq.lanxj.m.forum.service.MyMessageListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MyMessageListServiceImpl implements MyMessageListService{

    private static final Logger logger = LoggerFactory.getLogger(MyMessageListServiceImpl.class);


    @Resource
    MyMessageListMapper myMessageListMapper;


    @Override
    public List<MyMessageListVo> queryMymessageList() throws Exception {

        List<MyMessageListVo> myMessageListVolist=null;
        try{
        myMessageListVolist = myMessageListMapper.selectMyMessageList();
        }catch (Exception e) {
            logger.error("查询:{} 邀请列表信息异常！",e);
            throw new Exception("系统繁忙，请稍后重试！");
        }

        return myMessageListVolist;
    }




}
