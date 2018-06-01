package com.xyibq.lanxj.admin.forum.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SensitiveWordCorpusMpper {

    /**
     * 新增敏感词
     */
    public void insertSensitiveWord (@Param("sensitiveWord") String sensitiveWord);

    /**
     * 批量新增敏感词
     */
    public void insertBatchSensitiveWord (List<String> sensitiveWordList);

    /**
     * 修改敏感词
     */
    public void updateSensitiveWord(@Param("sensitiveWord") String sensitiveWord,@Param("oldSensitiveWord") String oldSensitiveWord);

    /**
     * 删除敏感词汇
     */
    public void deleteSensitiveWord(@Param("sensitiveWord") String sensitiveWord);

    /**
     * 敏感词模糊查询
     */
    public List<String> selectSensitiveWord(@Param("sensitiveWord") String sensitiveWord);
}
