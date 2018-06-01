package com.xyibq.lanxj.admin.forum.service.impl;

import com.xyibq.lanxj.admin.forum.domain.entity.UserBlackListEntity;
import com.xyibq.lanxj.admin.forum.mapper.UserBlackListMapper;
import com.xyibq.lanxj.admin.forum.service.UserBlackListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserBlackListServiceImpl implements UserBlackListService{

    @Resource
    UserBlackListMapper userBlackListMapper;


    @Override
    public List<UserBlackListEntity> queryUserBlackList() {

        List<UserBlackListEntity> userBlackListEntities=null;
        try {
           userBlackListEntities = userBlackListMapper.selectUserblackList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userBlackListEntities;
    }

    @Override
    public int addUserintoBlackList(UserBlackListEntity userBlackListEntity) {
        int i = 0;
        Long userId = userBlackListEntity.getUserId();

        List<UserBlackListEntity> userBlackList = userBlackListMapper.selectblackUserbyUserid(userId);
       if(userBlackList.size()>0){
           // 说明已经存在  不插入
           return i;

       }else {
         //  int i = 0;
           try {
               i = userBlackListMapper.insertUserblackList(userBlackListEntity);
           } catch (Exception e) {
               e.printStackTrace();
           }

       }
        return i;
    }
}
