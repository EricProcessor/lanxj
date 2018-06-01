package com.xyibq.lanxj.admin.forum.web.controller.user;

import com.xyibq.lanxj.admin.forum.common.util.ResultUtil;
import com.xyibq.lanxj.admin.forum.domain.entity.UserBlackListEntity;
import com.xyibq.lanxj.admin.forum.service.UserBlackListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/userblacklistInfo")
public class UserBlackListController {

    @Resource
    UserBlackListService userBlackListService;

    @RequestMapping(value = "/finduserblacklist")
    @ResponseBody
    public  String findUserzBlackList(){
        List<UserBlackListEntity> userBlackList = userBlackListService.queryUserBlackList();
        Map<String,List> map = new HashMap<String,List>();
        map.put("userBlackList",userBlackList);
        return ResultUtil.successMsg(userBlackList);
      // return JSONResult.build(0,"成功",userBlackList);

    }


    @RequestMapping(value = "/insertuserblacklist")
    @ResponseBody
    public  String insertUserzBlackList(UserBlackListEntity userBlackListEntity){

        int i=0;
        try{
            i =  userBlackListService.addUserintoBlackList(userBlackListEntity);
        }catch (Exception e){
            return ResultUtil.errorMsg();
        }

        if(i>0){
            return ResultUtil.successMsg();
        }else{
            return ResultUtil.errorMsg();
        }

    }

}
