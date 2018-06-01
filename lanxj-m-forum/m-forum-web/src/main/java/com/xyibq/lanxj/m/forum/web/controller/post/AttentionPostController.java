package com.xyibq.lanxj.m.forum.web.controller.post;

import com.xyibq.lanxj.m.forum.domain.entity.AttentionPostEntity;
import com.xyibq.lanxj.m.forum.domain.vo.JSONResult;
import com.xyibq.lanxj.m.forum.service.AttentionPostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/attentionpost")
public class AttentionPostController {

    @Resource
    AttentionPostService attentionPostService;


    /*
    * 关注帖子
    * @Param 用户id
    * @Param 帖子id
    * @Param 关注状态
    * */
    @RequestMapping(value = "attentionpostbystatus")
    @ResponseBody
    private JSONResult attentionpostBystatus(String userid,String postid,Long attentionstatus) throws Exception{

        //查询关注帖子列表，如果该用户下没有关注过该帖子，不存在，向数据库插入，否则，进行更新操作
        List<AttentionPostEntity> attentionPostEntitiesList = attentionPostService.queryAttentionpostbyuseridandpostid(userid, postid);
        //List<AttentionPostEntity> attentionPostEntitiesList = attentionPostService.queryAttentionpostbyuseridandpostid(map);
        if(attentionPostEntitiesList.size()>0){
            //存在 更新操作
            AttentionPostEntity AttentionPostEntity=attentionPostEntitiesList.get(0);
            AttentionPostEntity.setAttentionStatus(attentionstatus);
            attentionPostService.updateAttentionPostInfo(AttentionPostEntity);
            return JSONResult.ok();
        }else{
            // 说明不存在 新增操作
            long postId = Long.parseLong(postid);
            long userId = Long.parseLong(userid);

            AttentionPostEntity attentionPostEntity=new AttentionPostEntity();
            attentionPostEntity.setAttentionStatus(attentionstatus);
            attentionPostEntity.setPostId(postId);
            attentionPostEntity.setUserId(userId);
            attentionPostEntity.setModifyTime(new Date());
            attentionPostService.addAttentionPostInfo(attentionPostEntity);
            return JSONResult.ok();
        }

    }


}
