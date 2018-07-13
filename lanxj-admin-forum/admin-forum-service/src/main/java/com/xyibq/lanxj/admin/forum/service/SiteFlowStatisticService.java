package com.xyibq.lanxj.admin.forum.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 论坛流量统计
 */
public interface SiteFlowStatisticService {

    /**
     * 日统计，按小时展示PV
     */
    public List<Map<String,String>> showPVperHourInDay(String collectDate);

    /**
     * 月统计，按日展示PV
     */
    public  List<Map<String,String>> showPVperDayInMonth(String collectMonth);
}
