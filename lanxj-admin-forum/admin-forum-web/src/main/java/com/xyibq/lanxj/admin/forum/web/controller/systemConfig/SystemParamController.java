package com.xyibq.lanxj.admin.forum.web.controller.systemConfig;

import com.xyibq.lanxj.admin.forum.common.util.CheckUtil;
import com.xyibq.lanxj.admin.forum.common.util.ResultUtil;
import com.xyibq.lanxj.admin.forum.service.SystemParameterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/systemParam")
public class SystemParamController {

    private static final Logger logger = LoggerFactory.getLogger(SystemParamController.class);

    private  static final String POST_FORBIDDEN_TIME_PARAM_NAME = "post_forbidden_time";

    @Resource
    SystemParameterService systemParameterService;

    /**
     * 查询发帖时间限制
     */
    @ResponseBody
    @RequestMapping("/queryPostForbiddenTime")
    public String queryPostForbiddenTime(){

        String postForbiddenTime = systemParameterService.queryPostForbiddenTime(POST_FORBIDDEN_TIME_PARAM_NAME);
        Map<String,String> map = new HashMap<String,String>();
        if(!CheckUtil.checkEmpty(postForbiddenTime)){
            String time[] = postForbiddenTime.split("||");
            String amStartTime = time[0].split("-")[0];
            String amEndTime = time[0].split("-")[1];
            String pmStartTime = time[1].split("-")[0];
            String pmEndTime = time[1].split("-")[1];

            map.put("amStartTime",amStartTime);
            map.put("amEndTime",amEndTime);
            map.put("pmStartTime",pmStartTime);
            map.put("pmEndTime",pmEndTime);
        }

        return ResultUtil.successMsg(map);
    }

    /**
     * 设置发帖时间限制
     */
    @ResponseBody
    @RequestMapping("/setPostForbiddenTime")
    public String setPostForbiddenTime(HttpServletRequest request){

        String amStartTime = request.getParameter("amStartTime");
        String amEndTime = request.getParameter("amEndTime");
        String pmStartTime = request.getParameter("pmStartTime");
        String pmEndTime = request.getParameter("pmEndTime");

        if(CheckUtil.checkEmpty(amStartTime)){
            return ResultUtil.errorMsg("上午起始时间不能为空！");
        }
        if(CheckUtil.checkEmpty(amEndTime)){
            return ResultUtil.errorMsg("上午截至时间不能为空！");
        }
        if(CheckUtil.checkEmpty(pmStartTime)){
            return ResultUtil.errorMsg("下午起始时间不能为空！");
        }
        if(CheckUtil.checkEmpty(pmEndTime)){
            return ResultUtil.errorMsg("下午截至时间不能为空！");
        }

        //拼接禁止发帖 时间数据 格式：9:00-11:30||13:30-17:30
        String postForbiddenTime = amStartTime+"-"+amEndTime+"||"+pmStartTime+"-"+pmEndTime;
        Map<String,String> map = new HashMap<String,String>();
        map.put("postForbiddenTimeParaName",POST_FORBIDDEN_TIME_PARAM_NAME);
        map.put("postForbiddenTimeParaValue",postForbiddenTime);

        try {
            systemParameterService.setPostForbiddenTime(map);
        } catch (Exception e) {
            logger.error("设置禁止发帖时间异常！",e);
            return  ResultUtil.errorMsg();
        }

        return ResultUtil.successMsg();
    }
}
