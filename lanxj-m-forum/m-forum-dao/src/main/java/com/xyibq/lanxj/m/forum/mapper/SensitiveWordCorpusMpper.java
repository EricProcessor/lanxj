package com.xyibq.lanxj.m.forum.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.Set;

@Mapper
@Component
public interface SensitiveWordCorpusMpper {

    /**
     * 查询敏感词库
     */
   public Set<String>  SensitiveWordCorpusInit();
}
