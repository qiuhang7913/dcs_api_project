package com.huaching.xa.campus.common.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledFuture;

/**
 * <p>动态定时任务service</p>
 *
 * @author qiuhang
 * @version v1.0 2020/07/24
 */
@Service
public class DynamicScheduledTaskService {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private ScheduledFuture<?> future;

    /**
     * 开启定时任务
     *
     * @param myScheduledRunnable
     * @param cron
     * @return
     */
    public String startCron(MyScheduledRunnable myScheduledRunnable, String cron) {
        future = threadPoolTaskScheduler.schedule(myScheduledRunnable, new CronTrigger(cron));
        return "ok!";
    }


    /**
     * 停止定时任务
     *
     * @return
     */
    public String stopCron() {
        if (future != null) {
            future.cancel(true);

        }
        return "stop";
    }
}
