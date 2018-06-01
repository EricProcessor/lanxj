package com.xyibq.lanxj.m.forum.web.controller.post;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyibq.lanxj.m.forum.domain.vo.InviteCommentsVo;
import com.xyibq.lanxj.m.forum.domain.vo.JSONResult;
import com.xyibq.lanxj.m.forum.service.InviteCommentsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/inviteComments")
public class InviteCommentsController {

    @Resource
    InviteCommentsService inviteCommentsService;

    //分页
    //是否展示帖子邀请评论按钮  实际中是在帖子详情页面加标志判断，这里只做查询去重，展示除本身其他符合有权限的用户
    @RequestMapping(value = "inviteuserListbyMap")
    @ResponseBody
    private String inviteUserCommentListbyMap(HttpServletRequest request, HttpServletResponse response) throws Exception{

        String queryinviteUserMap = request.getParameter("queryinviteUserMap");
        Map map = JSON.parseObject(queryinviteUserMap,Map.class);
        int pageSize = (int)map.get("pageSize");
        int pageNum = (int)map.get("pageNum");
        Integer Userid = (Integer)map.get("userid");
        String userid=Userid.toString();
//        String userid=  request.getParameter("userid");
//        String pagesize =request.getParameter("pageSize");
//        String pagenum =request.getParameter("pageNum");
//        int pageSize=Integer.parseInt(pagesize);
//        int pageNum=Integer.parseInt(pagenum);

//        Integer pageSize = (Integer)map.get("pageSize");
//        Integer pageNum = (Integer)map.get("pageNum");

        //为了程序的严谨性，判断非空：
        /* if(pagenum == null){
             pageNum = 1;   //设置默认当前页
            }
        if(pageNum <= 0){
            pageNum = 1;
             }
        if(pagesize == null){
            pageSize = 10;    //设置默认每页显示的商品数(因为jsp页面上默认写的就是30条)
            }*/

        //page分页
        PageHelper.startPage(pageNum,pageSize);

        Map<String, Object> queryMap=new HashMap<String, Object>();
        List<String> useridlist = new ArrayList<String>();
        useridlist.add(userid);
        queryMap.put("userIdList",useridlist);
        queryMap.put("invitecommentauth",1);


        // 有邀请权限  查询除自身之外的所有用户信息
        List<InviteCommentsVo> inviteCommentsVosList = inviteCommentsService.queryInviteCommentsByMap(queryMap);

        PageInfo<InviteCommentsVo> inviteCommentPageInfo = new PageInfo<InviteCommentsVo>(inviteCommentsVosList);

        return JSON.toJSONString(inviteCommentPageInfo);
        //return  JSONResult.build(0,"成功",inviteCommentList);
        //{"status":0,"msg":"成功","data":[{"id":3,"userId":1003,"userName":"gcr128","userType":1,"position":"技术研发岗","inviteCommentAuth":1,"remark":"qqq"}],"ok":null}
        //{"status":0,"msg":"成功","data":[{"id":3,"userId":1003,"userName":"gcr128","userType":1,"position":"技术研发岗","inviteCommentAuth":1,"remark":"qqq"},{"id":4,"userId":1004,"userName":"天天","userType":0,"position":"技术研发岗","inviteCommentAuth":1,"remark":"33"}],"ok":null}
    }



    @RequestMapping(value = "inviteuserListAll")
    @ResponseBody
    private JSONResult inviteUserComments(String userid) throws Exception{

        List<InviteCommentsVo> inviteCommentList=new ArrayList<InviteCommentsVo>();
        List<InviteCommentsVo> inviteCommentsVoList = inviteCommentsService.queryInviteCommentsByUserId(userid);
           if(inviteCommentsVoList.size()>0){
               InviteCommentsVo inviteCommentsVo = inviteCommentsVoList.get(0);
               Long inviteCommentAuth = inviteCommentsVo.getInviteCommentAuth();
               //如果为0 说明无 邀请权限
               if(inviteCommentAuth.toString().equals("0")){
                   Object object=new Object();
                   //Integer status, String msg, Object data
                  // JSONResult.build(0,"成功",object);
                   //return JSONResult.build(0,"成功",object);
                   return JSONResult.ok();
               }else if(inviteCommentAuth.toString().equals("1")){
                   // 有邀请权限  查询除自身之外的所有用户信息
                   List<InviteCommentsVo> inviteCommentsVosList = inviteCommentsService.queryInviteCommentsByinviteauth("1");
                  if(inviteCommentsVosList.size()>0){
                      for (InviteCommentsVo InviteCommentsVo:inviteCommentsVosList){

                          Long userId = InviteCommentsVo.getUserId();
                          if(userId.toString().equals(userid)){
                              continue;
                          }
                          inviteCommentList.add(InviteCommentsVo);
                      }
                  }
               }
           }
        return  JSONResult.build(0,"成功",inviteCommentList);
          //{"status":0,"msg":"成功","data":[{"id":3,"userId":1003,"userName":"gcr128","userType":1,"position":"技术研发岗","inviteCommentAuth":1,"remark":"qqq"}],"ok":null}
       // {"status":0,"msg":"成功","data":[{"id":3,"userId":1003,"userName":"gcr128","userType":1,"position":"技术研发岗","inviteCommentAuth":1,"remark":"qqq"},{"id":4,"userId":1004,"userName":"天天","userType":0,"position":"技术研发岗","inviteCommentAuth":1,"remark":"33"}],"ok":null}
       // return  JSONObject.toJSONString(inviteCommentList);
    }


    //是否展示帖子邀请评论按钮  实际中是在帖子详情页面加标志判断，这里只做查询去重，展示除本身其他符合有权限的用户
    @RequestMapping(value = "inviteuserList")
    @ResponseBody
    private JSONResult inviteUserCommentList(String userid) throws Exception{
        // private JSONResult inviteUserCommentList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //String userid=  request.getParameter("userid");
        List<InviteCommentsVo> inviteCommentList=new ArrayList<InviteCommentsVo>();
        // 有邀请权限  查询除自身之外的所有用户信息
        List<InviteCommentsVo> inviteCommentsVosList = inviteCommentsService.queryInviteCommentsByinviteauth("1");
        if (inviteCommentsVosList.size() > 0) {
            for (InviteCommentsVo InviteCommentsVo : inviteCommentsVosList) {

                Long userId = InviteCommentsVo.getUserId();
                if (userId.toString().equals(userid)) {
                    continue;
                }
                inviteCommentList.add(InviteCommentsVo);
            }
        }
        return  JSONResult.build(0,"成功",inviteCommentList);
        //{"status":0,"msg":"成功","data":[{"id":3,"userId":1003,"userName":"gcr128","userType":1,"position":"技术研发岗","inviteCommentAuth":1,"remark":"qqq"}],"ok":null}
        //{"status":0,"msg":"成功","data":[{"id":3,"userId":1003,"userName":"gcr128","userType":1,"position":"技术研发岗","inviteCommentAuth":1,"remark":"qqq"},{"id":4,"userId":1004,"userName":"天天","userType":0,"position":"技术研发岗","inviteCommentAuth":1,"remark":"33"}],"ok":null}
    }
}
