package com.xyibq.lanxj.m.forum.service.impl;

import com.xyibq.lanxj.m.forum.mapper.SensitiveWordCorpusMpper;
import com.xyibq.lanxj.m.forum.service.SensitiveWordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class SensitiveWordServiceImpl implements SensitiveWordService {

    @Resource
    SensitiveWordCorpusMpper sensitiveWordCorpusMpper;

    /**
     * 查询敏感词库
     */
    public Set<String> SensitiveWordCorpusInit(){
        return  sensitiveWordCorpusMpper.SensitiveWordCorpusInit();
    }
}
