package com.xyibq.lanxj.admin.forum.service.impl;

import com.xyibq.lanxj.admin.forum.mapper.SiteFlowStatisticMapper;
import com.xyibq.lanxj.admin.forum.service.SiteFlowStatisticService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 论坛流量统计
 */
@Service
public class SiteFlowStatisticServiceImpl implements SiteFlowStatisticService{

    @Resource
    SiteFlowStatisticMapper siteFlowStatisticMapper;

    /**
     * 日统计，按小时展示PV
     */
    public List<Map<String,String>> showPVperHourInDay(String collectDate){
        return siteFlowStatisticMapper.selectPVperHourInDay(collectDate);
    }

    /**
     * 月统计，按日展示PV
     */
    public  List<Map<String,String>> showPVperDayInMonth(String collectMonth){
        return siteFlowStatisticMapper.selectPVperDayInMonth(collectMonth);
    }
}
