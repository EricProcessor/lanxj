package com.xyibq.lanxj.admin.forum.service.impl;

import com.xyibq.lanxj.admin.forum.mapper.SensitiveWordCorpusMpper;
import com.xyibq.lanxj.admin.forum.service.SensitiveWordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 敏感词维护服务
 */
@Service
public class SensitiveWordServiceImpl implements SensitiveWordService {

    private static final Logger logger = LoggerFactory.getLogger(ForumPostInfoServiceImpl.class);

    @Resource
    SensitiveWordCorpusMpper sensitiveWordCorpusMpper;

    /**
     * 新增敏感词
     */
    public void addSensitiveWord (String sensitiveWord){
        sensitiveWordCorpusMpper.insertSensitiveWord(sensitiveWord);
    }

    /**
     * 批量新增敏感词
     */
    public void addBatchSensitiveWord (List<String> sensitiveWordList){
        sensitiveWordCorpusMpper.insertBatchSensitiveWord(sensitiveWordList);
    }

    /**
     * 修改敏感词
     */
    public void modifySensitiveWord(String sensitiveWord,String oldSensitiveWord){
        sensitiveWordCorpusMpper.updateSensitiveWord(sensitiveWord,oldSensitiveWord);
    }

    /**
     * 删除敏感词汇
     */
    public void removeSensitiveWord(String sensitiveWord){
        sensitiveWordCorpusMpper.deleteSensitiveWord(sensitiveWord);
    }

    /**
     * 模糊查询敏感词汇
     */
    public void querySensitiveWord(String sensitiveWord){
        sensitiveWordCorpusMpper.selectSensitiveWord(sensitiveWord);
    }
}
