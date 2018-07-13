package com.xyibq.lanxj.admin.forum.mapper;

import com.xyibq.lanxj.admin.forum.domain.entity.PostPicUrlRelateEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PostPicUrlRelateMapper {

    /**
     * 批量添加论坛图片url
     */
    public int batchInsertPostPicUrl(List<PostPicUrlRelateEntity> postPicUrlList);

    /**
     * 查询论坛图片url
     */
    public List<PostPicUrlRelateEntity> selectPostPicUrlList(String postId);
}
