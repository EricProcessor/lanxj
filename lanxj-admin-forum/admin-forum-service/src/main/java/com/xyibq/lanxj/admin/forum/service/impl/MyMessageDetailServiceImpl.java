package com.xyibq.lanxj.admin.forum.service.impl;


import com.xyibq.lanxj.admin.forum.domain.entity.MyMessageDetailEntity;
import com.xyibq.lanxj.admin.forum.mapper.MyMessageDetailMapper;
import com.xyibq.lanxj.admin.forum.service.MyMessageDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyMessageDetailServiceImpl implements MyMessageDetailService {

    private static final Logger logger = LoggerFactory.getLogger(MyMessageDetailServiceImpl.class);
    @Resource
    MyMessageDetailMapper myMessageDetailMapper;

    /**
     * 查询我的消息详情列表
     * @param userid
     * @param msg_id
     */
    @Override
    public List<MyMessageDetailEntity> queryMessageDetailList(Long msg_id, String userid) throws Exception {

        //针对不同类型的模板 查询条件需要做区分
//        1.收到的赞   10
//        2.收到的评论  20
//        3.关注帖子的赞 30
//        4.关注帖子的评论 40
//        5.关注帖子的邀请评论 50
//        6.收到邀请的评论提醒 60
        List<MyMessageDetailEntity> myMessageDetailListlist=null;
        MyMessageDetailEntity entity=new MyMessageDetailEntity();

        if(msg_id==10){

            entity.setReadYn(new Long(0));
            entity.setMsgId(msg_id);
            //entity.setPostPrefixContext("dw我");
            //当前用户id，即发帖者的id
            Long postuserId=Long.parseLong(userid);
            entity.setPostuserId(postuserId);
            myMessageDetailListlist=this.queryMessDtlbyType(entity);
            /*try{
                myMessageDetailListVolist =  myMessageDetailMapper.selectMyMessageDetailList(entity);
            }catch (Exception e) {
                logger.error("查询:{} 消息明细列表信息异常！",e);
                throw new Exception("系统繁忙，请稍后重试！");
            }*/
        }else if(msg_id==20){

            entity.setReadYn(new Long(0));
            entity.setMsgId(msg_id);
            //当前用户id，即发帖者的id
            Long postuserId=Long.parseLong(userid);
            entity.setPostuserId(postuserId);
            myMessageDetailListlist=this.queryMessDtlbyType(entity);
           /* try{
                myMessageDetailListVolist = myMessageDetailMapper.selectMyMessageDetailList(entity);
            }catch (Exception e) {
                logger.error("查询:{} 消息明细列表信息异常！",e);
                throw new Exception("系统繁忙，请稍后重试！");
            }*/
        }else if(msg_id==30){

            entity.setReadYn(new Long(0));
            entity.setMsgId(msg_id);
            //当前用户id，即关注帖子ID的用户ID
            Long attentuserId=Long.parseLong(userid);
            entity.setAttentuserId(attentuserId);
            myMessageDetailListlist=this.queryMessDtlbyType(entity);
            /*try{
                myMessageDetailListVolist = myMessageDetailMapper.selectMyMessageDetailList(entity);
            }catch (Exception e) {
                logger.error("查询:{} 消息明细列表信息异常！",e);
                throw new Exception("系统繁忙，请稍后重试！");
            }*/
        }else if(msg_id==40){

            entity.setReadYn(new Long(0));
            entity.setMsgId(msg_id);
            //当前用户id，即关注帖子ID的用户ID
            Long attentuserId=Long.parseLong(userid);
            entity.setAttentuserId(attentuserId);
            myMessageDetailListlist=this.queryMessDtlbyType(entity);
//            try{
//                myMessageDetailListVolist = myMessageDetailMapper.selectMyMessageDetailList(entity);
//            }catch (Exception e) {
//                logger.error("查询:{} 消息明细列表信息异常！",e);
//                throw new Exception("系统繁忙，请稍后重试！");
//            }
        }else if(msg_id==50){

            entity.setReadYn(new Long(0));
            entity.setMsgId(msg_id);
            // Long userId=Long.parseLong(userid);
            //当前用户id，即关注帖子邀请人ID的用户ID
            Long attentuserId=Long.parseLong(userid);
            entity.setAttentuserId(attentuserId);

            myMessageDetailListlist = this.queryMessDtlbyType(entity);
//            try{
//                myMessageDetailListVolist = myMessageDetailMapper.selectMyMessageDetailList(entity);
//            }catch (Exception e) {
//                logger.error("查询:{} 消息明细列表信息异常！",e);
//                throw new Exception("系统繁忙，请稍后重试！");
//            }
        }else if(msg_id==60){

            entity.setReadYn(new Long(0));
            entity.setMsgId(msg_id);
            // Long userId=Long.parseLong(userid);
            //当前用户id，即关注帖子邀请人ID的用户ID
            Long attentuserId=Long.parseLong(userid);
            entity.setAttentuserId(attentuserId);

            myMessageDetailListlist = this.queryMessDtlbyType(entity);
//            try{
//                myMessageDetailListVolist = myMessageDetailMapper.selectMyMessageDetailList(entity);
//            }catch (Exception e) {
//                logger.error("查询:{} 消息明细列表信息异常！",e);
//                throw new Exception("系统繁忙，请稍后重试！");
//            }
        }

       /* List<MyMessageDetailEntity> myMessageDetailListVolist=null;
        Map<String,Object> map = new HashMap<String,Object>();
        Long userId=Long.parseLong(userid);
        map.put("msgId", msg_id);
        map.put("userId",userId);

        try{
            myMessageDetailListVolist = myMessageDetailMapper.selectMyMessDtlbycondition(map);
        }catch (Exception e) {
            logger.error("查询:{} 消息明细列表信息异常！",e);
            throw new Exception("系统繁忙，请稍后重试！");
        }*/
        return myMessageDetailListlist;
    }

    @Override
    public List<MyMessageDetailEntity> queryMessageDetailAllList(Long msg_id, String userid) throws Exception {
        List<MyMessageDetailEntity> myMessageDetailListVolist=null;
        Map<String,Object> map = new HashMap<String,Object>();
        Long userId=Long.parseLong(userid);
        map.put("msgId", msg_id);
        map.put("userId",userId);

        try{
            myMessageDetailListVolist = myMessageDetailMapper.selectMyMessDtlbyusermsgId(map);
        }catch (Exception e) {
            logger.error("查询:{ALL} 消息明细列表信息异常！",e);
            throw new Exception("系统繁忙，请稍后重试！");
        }
        return myMessageDetailListVolist;
    }


    /**
     * 查询我的消息未读列表
     * @param userId
     */
    @Override
    public List<MyMessageDetailEntity> queryUnReadmessageList(Long userId) throws Exception {
        List<MyMessageDetailEntity> myMessageDetailList=null;

        try{
            myMessageDetailList = myMessageDetailMapper.selectUnReadMessDtlbyuserid(userId);
        }catch (Exception e) {
            logger.error("查询:{} 消息明细信息异常！",e);
            throw new Exception("系统繁忙，请稍后重试！");
        }

        return myMessageDetailList;
    }

    /**
     * 查询我的消息未读数量 （包含所有消息类型ID）
     * @param userId
     */
    @Override
    public int queryUnReadMesscountbyuserid(Long userId) throws Exception {


        Long postuserId=userId;
        int i = myMessageDetailMapper.selectUnReadMesscountbyuserid(postuserId);

        return i;
    }


    /**
     * 查询我的消息详情列表
     * 通过条件模糊查询
     * @param entity
     */
    @Override
    public List<MyMessageDetailEntity> queryMymessageDetaillist(MyMessageDetailEntity entity) throws Exception {
        return null;
    }


    /**
     *
     * 更新我的消息详情表 把未读状态修改成已读
     * @param entity
     */
    @Override
    @Transactional
    public int modifyMyMessageDetailInfo(MyMessageDetailEntity entity) throws Exception {

        int i = myMessageDetailMapper.updateMyMessageDetailInfo(entity);

        return i;
    }





    public List<MyMessageDetailEntity> queryMessDtlbyType(MyMessageDetailEntity entity) throws Exception{

        List<MyMessageDetailEntity> myMessageDetailListVolist = null;
        try {
            myMessageDetailListVolist = myMessageDetailMapper.selectMyMessageDetailList(entity);
        } catch (Exception e) {
            logger.error("查询:{} 消息明细列表信息异常！", e);
            throw new Exception("系统繁忙，请稍后重试！");
        }
        return myMessageDetailListVolist;
    }


    /**
     *
     * 插入我的消息详情表 新增
     * @param entity
     */
    @Override
    @Transactional
    public int addMessageDetailInfo(MyMessageDetailEntity entity) throws Exception {

        int i = myMessageDetailMapper.insertMyMessageDetailInfo(entity);

        return i;
    }


}
