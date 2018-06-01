package com.xyibq.lanxj.admin.forum.mapper;

import com.xyibq.lanxj.admin.forum.domain.entity.UserTopicAuthEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserTopicAuthMapper {

    /**
     * 用户分配版块权限
     */
    public void insertUserTopicAuth(Map<String,Object> map);

    /**
     * 版主权限更新
     */
    public void updateUserTopicAuth(Map<String,Object> map);
}
