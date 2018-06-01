package com.xyibq.lanxj.admin.forum.service;

import com.xyibq.lanxj.admin.forum.domain.entity.UserBlackListEntity;

import java.util.List;

    public interface UserBlackListService {

        /**
         * 查询用户黑名单列表
         */
        public List<UserBlackListEntity> queryUserBlackList();


        /**
         * 新增黑名单
         */
        public int addUserintoBlackList(UserBlackListEntity userBlackListEntity);
}
