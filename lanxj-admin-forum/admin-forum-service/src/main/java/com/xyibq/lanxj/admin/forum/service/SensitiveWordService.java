package com.xyibq.lanxj.admin.forum.service;

import java.util.List;

/**
 * 敏感词维护服务
 */
public interface SensitiveWordService {

    /**
     * 新增敏感词
     */
    public void addSensitiveWord (String sensitiveWord);

    /**
     * 批量新增敏感词
     */
    public void addBatchSensitiveWord (List<String> sensitiveWordList);

    /**
     * 修改敏感词
     */
    public void modifySensitiveWord(String sensitiveWord,String oldSensitiveWord);

    /**
     * 删除敏感词汇
     */
    public void removeSensitiveWord(String sensitiveWord);

    /**
     * 模糊查询敏感词汇
     */
    public void querySensitiveWord(String sensitiveWord);


}
