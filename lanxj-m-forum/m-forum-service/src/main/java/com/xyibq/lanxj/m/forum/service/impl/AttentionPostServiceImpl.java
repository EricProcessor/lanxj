package com.xyibq.lanxj.m.forum.service.impl;

import com.xyibq.lanxj.m.forum.domain.entity.AttentionPostEntity;
import com.xyibq.lanxj.m.forum.mapper.AttentionPostMapper;
import com.xyibq.lanxj.m.forum.service.AttentionPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttentionPostServiceImpl implements AttentionPostService{

    private static final Logger logger = LoggerFactory.getLogger(AttentionPostServiceImpl.class);

    @Resource
    AttentionPostMapper attentionPostMapper;

    /**
     * 查询关注帖子信息
     */
    @Override
    public List<AttentionPostEntity> queryAttentionpostbyuseridandpostid(String userId, String postId) throws Exception {

        List<AttentionPostEntity> attentionPostEntitiesList = null;
        Map<String,Object> map = new HashMap<String,Object>();
        Long userid=Long.parseLong(userId);
        Long postid=Long.parseLong(postId);

        map.put("userId", userid);
        map.put("postId",postid);

        try{
           // attentionPostEntitiesList = attentionPostMapper.selectAttentionpostByUserIdandPostId(userId, postId);
            attentionPostEntitiesList = attentionPostMapper.selectAttentionpostByUserIdandPostId(map);
        }catch (Exception e) {
            logger.error("查询:{} 关注帖子信息列表信息异常！",e);
            throw new Exception("系统繁忙，请稍后重试！");
        }
        return attentionPostEntitiesList;
    }

    /**
     * 新增关注帖子信息
     */
    @Override
    @Transactional
    public int addAttentionPostInfo(AttentionPostEntity attentionPostEntity) throws Exception {
        try {
            attentionPostMapper.insertAttentionPost(attentionPostEntity);
        }catch (Exception e){
            logger.error("新增关注帖子信息异常！",e);

            throw new Exception("系统繁忙，请稍后重试！");
        }
        return 1;
    }

    /**
     * 更新关注帖子信息
     */
    @Override
    @Transactional
    public int updateAttentionPostInfo(AttentionPostEntity attentionPostEntity) throws Exception {
        try {
            attentionPostMapper.updateAttentionPost(attentionPostEntity);
        }catch (Exception e){
            logger.error("更新关注帖子信息异常！",e);
            throw new Exception("系统繁忙，请稍后重试！");
        }
        return 0;
    }

    /**
     * 删除关注帖子信息
     */
    @Override
    @Transactional
    public int delAttentionPostByPrimaryKey(Long id) throws Exception {
        try {
            attentionPostMapper.deleteAttentionPostByPrimaryKey(id);
        }catch (Exception e){
            logger.error("删除关注帖子信息异常！",e);
            throw new Exception("系统繁忙，请稍后重试！");
        }
        return 0;
    }
}
