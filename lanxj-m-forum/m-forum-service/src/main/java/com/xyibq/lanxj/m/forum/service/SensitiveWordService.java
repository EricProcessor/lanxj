package com.xyibq.lanxj.m.forum.service;

import java.util.Set;

public interface SensitiveWordService {

    /**
     * 查询敏感词库
     */
    public Set<String> SensitiveWordCorpusInit();
}
