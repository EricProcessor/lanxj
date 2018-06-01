package com.xyibq.lanxj.admin.forum.web.controller.user;

import com.alibaba.fastjson.JSON;
import com.xyibq.lanxj.admin.forum.common.util.JSONResult;
import com.xyibq.lanxj.admin.forum.domain.entity.UserInfoEntity;
import com.xyibq.lanxj.admin.forum.service.UserInfoService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/userInfo")
public class UserInfoController {

    @Resource
    UserInfoService userInfoService;

    @Resource
    AmqpTemplate rabbitTemplate;

    /*
    * 根据用户id查询用户信息
    * @Param 用户id
    * */
    @RequestMapping(value = "userinfobyuserId")
    @ResponseBody
    private JSONResult UserInfoByuserid(String userid){

        //根据用户id查询用户信息
        List<UserInfoEntity> userList=null;
        try{
            userList = userInfoService.queryUserbyuserid(userid);
        }catch (Exception e){
            return JSONResult.errorMsg("查询错误");
        }

        if(userList.size()>0){
            UserInfoEntity userInfo = userList.get(0);
            return  JSONResult.build(200,"成功",userInfo);
        }else{
            // 说明不存在 为空
            return JSONResult.ok();
        }

    }

    /*
   * 根据姓名查询用户信息
   * @Param userName
   * */
    @RequestMapping(value = "userinfobyuserName")
    @ResponseBody
    private JSONResult UserInfoByuserName(String userName){
        //private JSONResult UserInfoByuserName(UserInfoEntity Entity){
        //String userName=Entity.getUserName();
        //根据用户id查询用户信息
        List<UserInfoEntity> userList=null;
        try{
            userList = userInfoService.queryUserbyuserName(userName);
        }catch (Exception e){
            return JSONResult.errorMsg("查询错误");
        }

        if(userList.size()>0){
            UserInfoEntity userInfo = userList.get(0);
            return  JSONResult.build(200,"成功",userInfo);
        }else{
            // 说明不存在 为空
            return JSONResult.ok();
        }
    }



    /*
  * 根据岗位名称查询用户信息
  * @Param userName
  * */
    @RequestMapping(value = "userinfobyPosition")
    @ResponseBody
    private JSONResult UserInfoByposition(String position){

        //根据用户id查询用户信息
        List<UserInfoEntity> userList=null;
        try{
            userList = userInfoService.queryUserbyposition(position);
        }catch (Exception e){
            return JSONResult.errorMsg("查询错误");
        }

        if(userList.size()>0){
          //  UserInfoEntity userInfo = userList.get(0);
            return  JSONResult.build(200,"成功",userList);
        }else{
            // 说明不存在 为空
            return JSONResult.ok();
        }
    }


    /*
  * 根据条件查询用户信息
  * @Param userInfoEntity
  * */
    @RequestMapping(value = "userinfobycondition")
    @ResponseBody
    private JSONResult UserInfoBycondition(UserInfoEntity userInfoEntity){

        //根据用户id查询用户信息
        List<UserInfoEntity> userList=null;
        try{
            userList = userInfoService.queryUser(userInfoEntity);
        }catch (Exception e){
            return JSONResult.errorMsg("查询错误");
        }

        if(userList.size()>0){
            return  JSONResult.build(200,"成功",userList);
        }else{
            // 说明不存在 为空
            return JSONResult.ok();
        }
    }

    /*
     * 新增用户信息
     * @Param userInfoEntity
     * */
    @RequestMapping(value = "insertuserinfo")
    @ResponseBody
    private JSONResult insertUserInfo(UserInfoEntity userInfoEntity){

        int i=0;
        try{
             i = userInfoService.addUserInfo(userInfoEntity);
        }catch (Exception e){
            return JSONResult.errorMsg("新增出现错误");
        }

        if(i>0){
            return JSONResult.ok();
           // return  JSONResult.build(200,"成功",userList);
        }else{
            // 插入数据不成功
            return JSONResult.errorMsg("插入数据不成功");
        }
    }



    /*
     * 修改用户信息
     * @Param userInfoEntity 需传主键
     * */
    @RequestMapping(value = "modifyuserinfo")
    @ResponseBody
    private JSONResult modifyUserInfo(UserInfoEntity userInfoEntity){

        int i=0;
        try{
            i = userInfoService.modifyUserInfo(userInfoEntity);
        }catch (Exception e){
            return JSONResult.errorMsg("更新出现错误");
        }

        if(i>0){
            return JSONResult.ok();
            // return  JSONResult.build(200,"成功",userList);
        }else{
            // 更新数据不成功 eg. 没有传主键
            return JSONResult.errorMsg("更新数据不成功");
        }
    }


    /*
    * 删除用户信息
    * @Param id 主键
    * */
    @RequestMapping(value = "removeuserinfobyId")
    @ResponseBody
    private JSONResult removeUserInfo(Long Id){

        int i=0;
        try{
            i = userInfoService.removeUserByPrimaryKey(Id);
        }catch (Exception e){
            return JSONResult.errorMsg("删除用户信息出现错误");
        }

        if(i>0){
            return JSONResult.ok();
            // return  JSONResult.build(200,"成功",userList);
        }else{
            // 更新数据不成功 eg. 没有传主键
            return JSONResult.errorMsg("删除数据不成功");
        }
    }



    /*
   * 用户发帖数量统计
   * @Param id 主键
   * 涉及用户表和帖子表
   * */
    @RequestMapping(value = "/usersendPostcount")
    @ResponseBody
    private JSONResult usersendPostcount(String userid){

        int count=0;
        try{
            count = userInfoService.UserSendPostcount(userid);
        }catch (Exception e){
            return JSONResult.errorMsg("查询统计用户发帖数量出现错误");
        }

        if(count>0){
            //return JSONResult.ok();
            return  JSONResult.build(200,"成功",count);
        }else{

            return JSONResult.ok();
        }
    }


    /*
  * 统计用户帖子总点赞数
  * @Param userid
  * 涉及帖子表和帖子点赞关联表
  * */
    @RequestMapping(value = "/userPostlikecount")
    @ResponseBody
    private JSONResult userPostlikecount(String userid){

        int count=0;
        try{
            count = userInfoService.UserPostlikecount(userid);
        }catch (Exception e){
            return JSONResult.errorMsg("查询统计用户点赞数量出现错误");
        }

        if(count>0){
            //return JSONResult.ok();
            return  JSONResult.build(200,"成功",count);
        }else{

            return JSONResult.ok();
        }
    }




    /**
     * 查询用户评论数
     * 涉及帖子表和帖子评论关联表
     */
    @RequestMapping(value = "/userSendCommentcount")
    @ResponseBody
    private JSONResult userSendCommentcount(String userid){

        int count=0;
        try{
            count = userInfoService.UserSendCommentcount(userid);
        }catch (Exception e){
            return JSONResult.errorMsg("查询用户评论数出现错误");
        }

        if(count>0){
            //return JSONResult.ok();
            return  JSONResult.build(200,"成功",count);
        }else{

            return JSONResult.ok();
        }
    }

   /*
    *
    * rabbit test
    * */
    @RequestMapping(value = "test")
    @ResponseBody
    private void test(String result){

        //组装要发送的消息内容

        UserInfoEntity userInfoEntity = new UserInfoEntity();

        userInfoEntity.setId(new Long(1));
        userInfoEntity.setInviteCommentAuth(new Long(1));
        userInfoEntity.setUserName("我成功了");
        //由于editCatalog正常执行的返回值是void，所以如果返回为null说明成功执行，可以开始发送消息，如果失败则什么都不发送
        if(result == null) {

            //使用已封装好的convertAndSend(String exchange , String routingKey , Object message)使用特定的routingKey发送消息到指定的exchange
            rabbitTemplate.convertAndSend("MessgeExchange" , "Maddcomment" , JSON.toJSONString(userInfoEntity));
        }

    }



}
