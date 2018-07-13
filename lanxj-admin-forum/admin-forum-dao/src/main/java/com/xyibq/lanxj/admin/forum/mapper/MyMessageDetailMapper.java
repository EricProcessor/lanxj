package com.xyibq.lanxj.admin.forum.mapper;


import com.xyibq.lanxj.admin.forum.domain.entity.MyMessageDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface MyMessageDetailMapper {


    /**
     * 根据条件封装实体类，模糊查询用户信息
     */
    public List<MyMessageDetailEntity> selectMyMessageDetailList(MyMessageDetailEntity Entity);



    /**
     * 根据用户信息和消息类型id查询消息明细表
     * @Map
     * @param userid
     * @param msg_id
     * yn=0 未读
     */
    public List<MyMessageDetailEntity> selectMyMessDtlbycondition(Map<String, Object> map);



    /**
     * 根据用户信息和消息类型id查询消息明细表
     * @Map
     * @param userid
     * @param msg_id
     */
    public List<MyMessageDetailEntity> selectMyMessDtlbyusermsgId(Map<String, Object> map);



    /**
     * 根据用户id查询消息明细表中未读数据
     * @param userId
     */
    public List<MyMessageDetailEntity> selectUnReadMessDtlbyuserid(Long userId);


    /**
     * 根据用户id查询消息明细表中未读数量
     * @param userId
     */
    public int selectUnReadMesscountbyuserid(Long userId);



    /**
     * 更新消息明细信息列表
     */
    public int updateMyMessageDetailInfo(MyMessageDetailEntity record);

    /**
     * 新增消息明细信息
     */
    public int insertMyMessageDetailInfo(MyMessageDetailEntity record);

}