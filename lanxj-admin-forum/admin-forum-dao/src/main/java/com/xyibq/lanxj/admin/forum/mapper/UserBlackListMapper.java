package com.xyibq.lanxj.admin.forum.mapper;

import com.xyibq.lanxj.admin.forum.domain.entity.UserBlackListEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserBlackListMapper {

    /**
     * 查询用户黑名单列表
     */
    public List<UserBlackListEntity> selectUserblackList();


    /**
     *新增用户到黑名单列表
     */
    public int insertUserblackList(UserBlackListEntity userBlackListEntity);


    /**
     * 查询用户黑名单列表通过userid
     */
    public List<UserBlackListEntity> selectblackUserbyUserid(Long userId);

}
