package com.xyibq.lanxj.admin.forum.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 数据统计表格
 */
@Mapper
public interface SiteFlowStatisticMapper {


    /**
     * 日统计，按小时展示PV
     */
    public List<Map<String,String>> selectPVperHourInDay(@Param("collectDate") String collectDate);

    /**
     * 月统计，按日展示PV
     */
    public List<Map<String,String>> selectPVperDayInMonth(@Param("collectMonth") String collectMonth);

}
