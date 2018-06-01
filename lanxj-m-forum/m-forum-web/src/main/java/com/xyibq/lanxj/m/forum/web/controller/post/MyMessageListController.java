package com.xyibq.lanxj.m.forum.web.controller.post;



import com.alibaba.fastjson.JSON;
import com.xyibq.lanxj.m.forum.domain.entity.ForumPostInfoEntity;
import com.xyibq.lanxj.m.forum.domain.entity.MyMessageDetailEntity;

import com.xyibq.lanxj.m.forum.domain.entity.UserInfoEntity;
import com.xyibq.lanxj.m.forum.domain.vo.JSONResult;
import com.xyibq.lanxj.m.forum.domain.vo.MyMessageListVo;
import com.xyibq.lanxj.m.forum.domain.vo.MyMessageVo;
import com.xyibq.lanxj.m.forum.service.MyMessageDetailService;
import com.xyibq.lanxj.m.forum.service.MyMessageListService;
import com.xyibq.lanxj.m.forum.service.UserInfoService;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/myMessageList")
public class MyMessageListController {

    @Resource
    MyMessageListService myMessageListService;

    @Resource
    MyMessageDetailService myMessageDetailService;


    @Resource
    UserInfoService userInfoService;


    /*
    * @gcr
    * 场景1 进入我的页面 展示界面需要的所有信息
    *
    * */
    @RequestMapping(value = "/queryMymessage")
    @ResponseBody
    private JSONResult queryMymessage(Long userid) throws Exception {

        MyMessageVo myMessageVo=new MyMessageVo();

        String user_Id=null;
        if(userid!=null){
            user_Id=userid.toString();
        }
        List<UserInfoEntity> userInfoList = userInfoService.queryUserbyuserid(user_Id);
        UserInfoEntity UserInfoEntity=userInfoList.get(0);

        int postlikecount = userInfoService.UserPostlikecount(user_Id);

        int sendCommentcount =userInfoService.UserSendCommentcount(user_Id);

        int sendPostcount =userInfoService.UserSendPostcount(user_Id);


        int i=0;
        try{

            //针对五种情况 分别统计汇总
            String userId = userid.toString();
            List<Long> list=new ArrayList<Long>();
            list.add(new Long(10));
            list.add(new Long(20));
            list.add(new Long(30));
            list.add(new Long(40));
            list.add(new Long(50));

            for(Long msgTypeId:list){
                List<MyMessageDetailEntity> MessageList = myMessageDetailService.queryMessageDetailList(msgTypeId, userId);
                i=i+MessageList.size();
            }

//             i = myMessageDetailService.queryUnReadMesscountbyuserid(userid);
        }catch (Exception e){
            return JSONResult.errorMsg("查询未读信息出现错误");
        }

        myMessageVo.setUserName(UserInfoEntity.getUserName()); //用户姓名
        myMessageVo.setPosition(UserInfoEntity.getPosition());  //用户岗位
        myMessageVo.setUserId(UserInfoEntity.getUserId());   //用户id
        myMessageVo.setPostcount(sendPostcount);   //发帖数
        myMessageVo.setPostlikecount(postlikecount);  //点赞数
        myMessageVo.setSendCommentcount(sendCommentcount); //评论数
        myMessageVo.setMyunreadmessagecount(i);
        return  JSONResult.build(200,"成功",myMessageVo);

    }

    /*
    * 场景 进入我的消息页面展示
    * 查询我的消息列表
    * */
    @RequestMapping(value = "/myMessagelist")
    @ResponseBody
    private JSONResult myMessageList(String userid) throws Exception {
        List<MyMessageListVo> myMessageListVoList=null;
        //1查询我的消息
        try{
            myMessageListVoList = myMessageListService.queryMymessageList();
        }catch (Exception e){
            return JSONResult.errorMsg("查询我的消息列表信息出现错误");
        }

        //2 根据获取的消息 根据userid查询消息明细表中的数据是否包含未读
        if(myMessageListVoList.size()>0){
            for (MyMessageListVo myMessageListVo:myMessageListVoList){
                Long msgTypeId = myMessageListVo.getMsgTypeId();
                List<MyMessageDetailEntity> MessageList = myMessageDetailService.queryMessageDetailList(msgTypeId, userid);

                if(MessageList.size()>0){
                    //如果存在 说明有未读信息
                    myMessageListVo.setReadYn(new Long(0));
                    myMessageListVo.setMessagecount(myMessageListVoList.size());
                }else{
                    myMessageListVo.setReadYn(new Long(1));
                }
            }
        }
        return  JSONResult.build(200,"成功",myMessageListVoList);

    }

    /*
    * 场景 进入我的  页面需要展示未读消息
    * 查询我的未读消息数量
    * */
    @RequestMapping(value = "/queryunReadmessageCount")
    @ResponseBody
    private JSONResult unReadmessageCount(Long userid) throws Exception {

        int i=0;
        try{

            //针对五种情况 分别统计汇总
            String userId = userid.toString();
            List<Long> list=new ArrayList<Long>();
            list.add(new Long(10));
            list.add(new Long(20));
            list.add(new Long(30));
            list.add(new Long(40));
            list.add(new Long(50));

            for(Long msgTypeId:list){
                List<MyMessageDetailEntity> MessageList = myMessageDetailService.queryMessageDetailList(msgTypeId, userId);
                i=i+MessageList.size();
            }

//             i = myMessageDetailService.queryUnReadMesscountbyuserid(userid);
        }catch (Exception e){
            return JSONResult.errorMsg("查询未读信息出现错误");
        }
        if(i>0){
            return  JSONResult.build(200,"成功",i);
        }else{
            // 说明不存在 为空
            return  JSONResult.build(200,"成功",0);
        }
    }





    /*
   * 场景 我的消息点击进入任意模板 未读的更新已读  已读的直接展示返回按时间倒序
   * 根据用户id和对应模板类型，查询我的消息列表
   * 打开后进行更新数据
   * msgTypeId
   * */
    @RequestMapping(value = "/queryunReadmessbymsgtypeanduserid")
    @ResponseBody
    private JSONResult unReadmessagelist(String userid, Long msgTypeId) throws Exception {

        List<MyMessageDetailEntity> myMessageDetailList=null;
        //1.查询我的消息 这里查询所有的
        try{
            // myMessageDetailList = myMessageDetailService.queryMessageDetailAllList(msgTypeId, userid);
            myMessageDetailList = myMessageDetailService.queryMessageDetailList(msgTypeId, userid);
        }catch (Exception e){
            return JSONResult.errorMsg("查询我的消息列表出现错误");
        }

        //2.存在，把未读的数据变成已读进行数据更新，循环执行单表更新
        if(myMessageDetailList.size()>0){
            for(MyMessageDetailEntity entity:myMessageDetailList){

                //如果当前读写状态数据是0 说明未读，进行更新已读操作
                if(entity.getReadYn().toString().equals("0")){
                    entity.setReadYn(new Long(1));
                    myMessageDetailService.modifyMyMessageDetailInfo(entity);
                }
            }
        }

        //3.模板详情中

        return  JSONResult.build(200,"成功",myMessageDetailList);
    }

    /*
    *
    * 根据用户id，查询我的未读消息列表
    * */
    @RequestMapping(value = "/queryunReadmessage")
    @ResponseBody
    private JSONResult unReadmessagelist(Long userid) throws Exception {

        List<MyMessageDetailEntity> myMessageDetailList=null;
        //1查询我的消息
        try{
            myMessageDetailList = myMessageDetailService.queryUnReadmessageList(userid);
        }catch (Exception e){
            return JSONResult.errorMsg("查询我的未读消息列表出现错误");
        }

        return  JSONResult.build(200,"成功",myMessageDetailList);
    }

    /*
    *
    * 消息触发  向消息明细表增加数据
    * */
    @RequestMapping(value = "/insertmessagedtl",method= RequestMethod.POST)
    @ResponseBody
    private JSONResult insertmessagedetail(MyMessageDetailEntity entity) throws Exception {


        System.out.print(entity.toString());

      int i=0;
        try{
             i=myMessageDetailService.addMessageDetailInfo(entity);
        }catch (Exception e){
            return JSONResult.errorMsg("插入消息列表出现错误");
        }

        if(i>0){
            return  JSONResult.build(200,"成功",i);
        }else{
            // 说明插入失败
            return  JSONResult.errorMsg("插入失败");
        }
    }

}
