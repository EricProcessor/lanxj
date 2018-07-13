package com.xyibq.lanxj.admin.forum.web.controller.systemConfig;

import com.xyibq.lanxj.admin.forum.common.util.CheckUtil;
import com.xyibq.lanxj.admin.forum.common.util.ResultUtil;
import com.xyibq.lanxj.admin.forum.service.SensitiveWordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/sensitiveWord")
public class SensitiveWordController {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveWordController.class);

    @Resource
    SensitiveWordService sensitiveWordService;

    /**
     * 新增敏感词
     */
    @ResponseBody
    @RequestMapping("/add")
    public String addSensitiveWord (String sensitiveWord){
        try {
            sensitiveWordService.addSensitiveWord(sensitiveWord);
        } catch (Exception e) {
            logger.error("新增敏感词失败！",e);
            return ResultUtil.errorMsg();
        }
        return ResultUtil.successMsg();
    }

    /**
     * 批量新增敏感词
     */
    @ResponseBody
    @RequestMapping("/addBatch")
    public String addBatchSensitiveWord (String sensitiveWords){

        if(CheckUtil.checkEmpty(sensitiveWords)){
            return ResultUtil.errorMsg("新增敏感词不可为空！");
        }

        //判断中英文 逗号
        List<String> sensitiveWordList = new ArrayList<String>();
        if (sensitiveWords.indexOf(",")>-1){
            sensitiveWordList = Arrays.asList(sensitiveWords.split(","));    //英文 逗号
        }else if(sensitiveWords.indexOf("，")>-1){
            sensitiveWordList = Arrays.asList(sensitiveWords.split("，"));   //中文 逗号
        }else{
            return ResultUtil.errorMsg("请用（逗号，）隔开多个词汇 正确填写！");
        }

        try {
            sensitiveWordService.addBatchSensitiveWord(sensitiveWordList);
        } catch (Exception e) {
            logger.error("批量新增敏感词失败！",e);
            return ResultUtil.errorMsg();
        }
        return ResultUtil.successMsg();
    }

    /**
     * 修改敏感词
     */
    @ResponseBody
    @RequestMapping("/modify")
    public String modifySensitiveWord(String sensitiveWord,String oldSensitiveWord){
        try {
            sensitiveWordService.modifySensitiveWord(sensitiveWord,oldSensitiveWord);
        } catch (Exception e) {
            logger.error("修改敏感词失败！",e);
            return ResultUtil.errorMsg();
        }
        return ResultUtil.successMsg();
    }

    /**
     * 删除敏感词汇
     */
    @ResponseBody
    @RequestMapping("/remove")
    public String removeSensitiveWord(String sensitiveWord){
        try {
            sensitiveWordService.removeSensitiveWord(sensitiveWord);
        } catch (Exception e) {
            logger.error("删除敏感词汇失败！",e);
            return ResultUtil.errorMsg();
        }
        return ResultUtil.successMsg();
    }

    /**
     * 模糊查询敏感词汇
     */
    @ResponseBody
    @RequestMapping("/query")
    public String querySensitiveWord(String sensitiveWord){
        try {
            sensitiveWordService.querySensitiveWord(sensitiveWord);
        } catch (Exception e) {
            logger.error("模糊查询敏感词汇失败！",e);
            return ResultUtil.errorMsg();
        }
        return ResultUtil.successMsg();
    }


}
