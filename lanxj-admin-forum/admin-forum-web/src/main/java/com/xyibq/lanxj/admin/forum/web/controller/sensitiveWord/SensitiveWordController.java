package com.xyibq.lanxj.admin.forum.web.controller.sensitiveWord;

import com.xyibq.lanxj.admin.forum.common.util.ResultUtil;
import com.xyibq.lanxj.admin.forum.service.SensitiveWordService;
//import com.xyibq.lanxj.admin.forum.web.controller.postInfo.ForumTopicController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    @RequestMapping("/add")
    public String addSensitiveWord (HttpServletRequest request){
        String sensitiveWord = request.getParameter("sensitiveWord");
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
    @RequestMapping("/addBatch")
    public String addBatchSensitiveWord (HttpServletRequest request){
        String sensitiveWords = request.getParameter("sensitiveWords");
        List<String> sensitiveWordList = Arrays.asList(sensitiveWords.split(","));
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
    @RequestMapping("/modify")
    public String modifySensitiveWord(HttpServletRequest request){
        String sensitiveWord = request.getParameter("sensitiveWord");
        String oldSensitiveWord = request.getParameter("oldSensitiveWord");
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
    @RequestMapping("/remove")
    public String removeSensitiveWord(HttpServletRequest request){
        String sensitiveWord = request.getParameter("sensitiveWord");
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
    @RequestMapping("/query")
    public String querySensitiveWord(HttpServletRequest request){
        String sensitiveWord = request.getParameter("sensitiveWord");
        try {
            sensitiveWordService.querySensitiveWord(sensitiveWord);
        } catch (Exception e) {
            logger.error("模糊查询敏感词汇失败！",e);
            return ResultUtil.errorMsg();
        }
        return ResultUtil.successMsg();
    }


}
