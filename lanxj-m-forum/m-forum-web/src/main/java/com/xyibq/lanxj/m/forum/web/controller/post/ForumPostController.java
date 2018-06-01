package com.xyibq.lanxj.m.forum.web.controller.post;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xyibq.lanxj.m.forum.common.util.CheckUtil;
import com.xyibq.lanxj.m.forum.common.util.ResultUtil;
import com.xyibq.lanxj.m.forum.common.util.SensitiveWordUtil;
import com.xyibq.lanxj.m.forum.domain.entity.PostPicUrlRelateEntity;
import com.xyibq.lanxj.m.forum.domain.vo.ForumPostInfoDetailVo;
import com.xyibq.lanxj.m.forum.domain.entity.ForumPostInfoEntity;
import com.xyibq.lanxj.m.forum.service.ForumPostInfoService;
import com.xyibq.lanxj.m.forum.service.SensitiveWordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping("/postInfo")
public class ForumPostController {

    private static final Logger logger = LoggerFactory.getLogger(ForumPostController.class);

    @Resource
    ForumPostInfoService forumPostInfoService;
    @Resource
    SensitiveWordService sensitiveWordService;

    /**
     * 新增帖子
     */
    @ResponseBody
    @RequestMapping("/addPostInfo")
    public String addPostInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String postInfo = request.getParameter("postInfo");
        String postPicUrlList = request.getParameter("postPicUrlList");

        ForumPostInfoEntity forumPostInfoEntity = JSON.parseObject(postInfo,ForumPostInfoEntity.class);
        List<PostPicUrlRelateEntity> postPicUrlRelateEntityList = JSON.parseArray(postPicUrlList,PostPicUrlRelateEntity.class);

        if (CheckUtil.checkEmpty(forumPostInfoEntity)) {
            return ResultUtil.errorMsg();
        }

        if(CheckUtil.checkEmpty(forumPostInfoEntity.getUserId())){
            logger.error("发帖人userID不能为空");
            return ResultUtil.errorMsg();
        }

        if(CheckUtil.checkEmpty(forumPostInfoEntity.getPostContent())){
            return ResultUtil.errorMsg("发帖内容不能为空!");
        }

        //敏感词汇校验
        Set<String> sensitiveWordSet = sensitiveWordService.SensitiveWordCorpusInit();
        SensitiveWordUtil.init(sensitiveWordSet);
        boolean flag = SensitiveWordUtil.contains(forumPostInfoEntity.getPostContent(), SensitiveWordUtil.MinMatchTYpe);
        if (flag) {
                //获取敏感词个数
                // Set set = SensitiveWordUtil.getSensitiveWord(forumPostInfoEntity.getPostContent(), SensitiveWordUtil.MinMatchTYpe);
                // logger.error("帖子中含有敏感词汇的个数为：" + set.size() + "。包含：" + set);
            return ResultUtil.errorMsg("帖子中不得含有敏感词汇！");
        }

        Map<String,Object> postInfoMap = new HashMap<String,Object>();
        postInfoMap.put("forumPostInfoEntity",forumPostInfoEntity);
        postInfoMap.put("postPicUrlRelateEntityList",postPicUrlRelateEntityList);

        try {
            forumPostInfoService.addPostInfo(postInfoMap);
        } catch (Exception e) {
            logger.error("帖子新增异常！",e);
            return ResultUtil.errorMsg();
        }
        return ResultUtil.successMsg();
    }

    /**
     * 查询帖子详情
     */
    @ResponseBody
    @RequestMapping("/queryPostInfoByPostId")
    public String queryPostInfoByPostId(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String postId = request.getParameter("postId");

        if (CheckUtil.checkEmpty(postId)){
            logger.error("帖子ID不能为空");
            return ResultUtil.errorMsg();
        }

        ForumPostInfoDetailVo forumPostInfoDetailVo = new ForumPostInfoDetailVo();
        try {
            forumPostInfoDetailVo = forumPostInfoService.queryPostInfoByPostId(Long.valueOf(postId));
        } catch (Exception e) {
            logger.error("查询帖子:{} 详情异常！",postId,e);
            return ResultUtil.errorMsg();
        }
        return ResultUtil.successMsg(forumPostInfoDetailVo);
    }

    /**
     * 查询置顶帖子列表
     */
    @ResponseBody
    @RequestMapping("/queryTopPostsByTopicId")
    public String queryTopPostsByTopicId(HttpServletRequest request, HttpServletResponse response) throws Exception {


        String queryNoTopPostsByTopicIdMap = request.getParameter("queryTopPostsByTopicIdMap");
        Map map = JSON.parseObject(queryNoTopPostsByTopicIdMap,Map.class);

        if (CheckUtil.checkEmpty(map.get("topicId"))) {
            logger.error("版块ID不能为空");
            return ResultUtil.errorMsg();
        }

        List<ForumPostInfoDetailVo> forumPostsList = new ArrayList<ForumPostInfoDetailVo>();
        try{
            forumPostsList = forumPostInfoService.queryTopPostsByTopicId(map);
        } catch (Exception e) {
            logger.error("查询版块:{} 置顶帖子信息异常！",map.get("topicId"),e);
            return ResultUtil.errorMsg();
        }
        return JSONObject.toJSONString(forumPostsList);
    }

    /**
     * 查询【非】置顶帖子列表
     */
    @ResponseBody
    @RequestMapping("/queryNoTopPostsByTopicId")
    public String queryNoTopPostsByTopicId(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String queryNoTopPostsByTopicIdMap = request.getParameter("queryNoTopPostsByTopicIdMap");
        Map map = JSON.parseObject(queryNoTopPostsByTopicIdMap,Map.class);

        if (CheckUtil.checkEmpty(map.get("topicId"))) {
            logger.error("版块ID不能为空");
            return ResultUtil.errorMsg();
        }

        List<ForumPostInfoDetailVo> forumPostsList = new ArrayList<ForumPostInfoDetailVo>();
        try {
            forumPostsList = forumPostInfoService.queryNoTopPostsByTopicId(map);
        } catch (Exception e) {
            logger.error("根据版块:{} 查询帖子（未置顶）列表 异常！",map.get("topicId"),e);
            return ResultUtil.errorMsg();
        }
        return JSONObject.toJSONString(forumPostsList);
    }

    /**
     * 查询员工帖子动态列表
     */
    @ResponseBody
    @RequestMapping("/queryUserDynamicPostList")
    public String queryUserDynamicPostList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<ForumPostInfoDetailVo> forumPostsList = new ArrayList<ForumPostInfoDetailVo>();
        String queryUserDynamicPostReq = request.getParameter("queryUserDynamicPostReq");
        Map map = JSON.parseObject(queryUserDynamicPostReq,Map.class);

        if (CheckUtil.checkEmpty(map.get("userId"))) {
            logger.error("用户ID不能为空");
            return ResultUtil.errorMsg();
        }
        try {

            forumPostsList = forumPostInfoService.queryUserDynamicPostList(map);
        } catch (Exception e) {
            logger.error("XXXxxx 查询员工{}帖子动态列表错误 xxxXXX",map.get("userId"),e);
            return ResultUtil.errorMsg();
        }
        return ResultUtil.successMsg(forumPostsList);
    }

    /**
     * 删除帖子
     */
    @ResponseBody
    @RequestMapping("/removePostInfo")
    public String removePostInfo(HttpServletRequest request) throws Exception{
        String postId = request.getParameter("postId");

        if (CheckUtil.checkEmpty(postId)) {
            logger.error("帖子ID不能为空");
            return ResultUtil.errorMsg();
        }
        try {
            forumPostInfoService.removePostInfo(Long.valueOf(postId));
        } catch (Exception e) {
            logger.error("帖子{}删除失败！",postId);
            return ResultUtil.errorMsg();
        }
        return ResultUtil.successMsg();
    }



    /**
     * @gcr
     * 查询帖子详情
     */
    @ResponseBody
    @RequestMapping("/queryPostInfoByPostIdandtype")
    public String queryPostInfoByPostIdandtype(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String postId = request.getParameter("postId");

        String type = request.getParameter("type");



        if (CheckUtil.checkEmpty(postId)){
            logger.error("帖子ID不能为空");
            return ResultUtil.errorMsg();
        }
        ForumPostInfoDetailVo forumPostInfoDetailVo = new ForumPostInfoDetailVo();
        //针对查询详情时是邀请评论的 加type标志作为判断条件进行区分 1说明是邀请评论
        if("1".equals(type)){

            try {
                forumPostInfoDetailVo = forumPostInfoService.queryPostInfoByPostId(Long.valueOf(postId));
                forumPostInfoDetailVo.setType(new Integer(1));
            } catch (Exception e) {
                logger.error("查询帖子:{} 详情异常！",postId,e);
                return ResultUtil.errorMsg();
            }
        }else {
            try {
                forumPostInfoDetailVo = forumPostInfoService.queryPostInfoByPostId(Long.valueOf(postId));
            } catch (Exception e) {
                logger.error("查询帖子:{} 详情异常！",postId,e);
                return ResultUtil.errorMsg();
            }
        }


        return ResultUtil.successMsg(forumPostInfoDetailVo);
    }

}
