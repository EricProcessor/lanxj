package com.xyibq.lanxj.admin.forum.web.controller.systemConfig;

import com.alibaba.fastjson.JSONObject;
import com.xyibq.lanxj.admin.forum.common.util.DateUtil;
import com.xyibq.lanxj.admin.forum.common.util.ResultUtil;
import com.xyibq.lanxj.admin.forum.service.SiteFlowStatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 论坛数据统计
 */
@Controller
@RequestMapping("/pv")
public class SiteFlowStatisticController {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveWordController.class);

    @Resource
    SiteFlowStatisticService siteFlowStatisticService;

    /**
     * 查询当天的PV 按小时展示
     */
    @ResponseBody
    @RequestMapping("/day")
    public String showPVperHourInDay (String collectDate){

        List<Map<String,String>> hourPV = new ArrayList<Map<String,String>>();
        try {
             collectDate = DateUtil.getNowDate();
            hourPV = siteFlowStatisticService.showPVperHourInDay(collectDate);
        } catch (Exception e) {
            logger.error("查询当天的PV失败！",e);
            return ResultUtil.errorMsg();
        }
        return ResultUtil.successMsg(JSONObject.toJSONString(hourPV));
    }

    /**
     * 查询月PV统计 按日展示
     */
    @ResponseBody
    @RequestMapping("/month")
    public String showPVperDayInMonth (String collectMonth){

        List<Map<String,String>> dayPV = new ArrayList<Map<String,String>>();
        try {
            collectMonth = DateUtil.getNowMonth();
            dayPV =  siteFlowStatisticService.showPVperDayInMonth(collectMonth);
        } catch (Exception e) {
            logger.error("查询月PV统计失败！",e);
            return ResultUtil.errorMsg();
        }
        return ResultUtil.successMsg(JSONObject.toJSONString(dayPV));
    }




}
