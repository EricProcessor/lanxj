package com.xyibq.lanxj.admin.forum.scheduler;

import com.xyibq.lanxj.admin.forum.service.ForumPostInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;

@Component
public class ForumScheduler {

    private static final Logger logger = LoggerFactory.getLogger(ForumScheduler.class);

    @Resource
    ForumPostInfoService forumPostInfoService;

    //每天23点50分 自动将帖子置顶 将失效帖子取消置顶
    //@Scheduled(cron = "0 */1 * * * ?")   //测试每分触发
    @Scheduled(cron = "0 50 23 * * ?")
    public void cancelInvalePostTop() {

        //查询当日置顶要过期的帖子
        logger.info("【帖子自动取消置顶】 cancelInvalePostTop 【定时任务】开始.........");
        List<String>  willCancelPostList = forumPostInfoService.queryTodayWillCancelPostList();
        logger.info("【帖子自动取消置顶】 cancelInvalePostTop 【定时任务】查询出将失效的帖子列表："+willCancelPostList);

        //遍历取消置顶
        for(String postId : willCancelPostList){

            try {
                forumPostInfoService.cancelPostTop(postId);
                logger.info("【定时任务】【帖子自动取消置顶】，取消帖子:"+postId +" 置顶，成功!");
            } catch (Exception e) {
                logger.error("【定时任务】【帖子自动取消置顶】，取消帖子:"+postId +" 置顶，【失败】xxxxXXXXX",e);
            }
        }
    }


    //每天2点 自动从小易北汽获取员工信息
    //@Scheduled(cron = "0 */1 * * * ?")   //测试每分触发
    @Scheduled(cron = "0 0 2 * * ?")
    public void syncUserInfoForXiaoYi() {

        //查询当日置顶要过期的帖子
        logger.info("【定时任务】 syncUserInfoForXiaoYi 【同步北汽员工数据】开始.........");

    }

}
