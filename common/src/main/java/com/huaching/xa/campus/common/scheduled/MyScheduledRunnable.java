package com.huaching.xa.campus.common.scheduled;

import com.huaching.xa.campus.basic.util.DateTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;


/**
 * <p>动态定时任务执行基类</p>
 *
 * @author qiuhang
 * @version v1.0 2020/07/24
 */
public abstract class MyScheduledRunnable implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run() {
        logger.info("starting task:****************" + DateTool.getDataStrByLocalDateTime(LocalDateTime.now(),DateTool.FORMAT_L6));
        excute();
        logger.info("ending task:****************" + DateTool.getDataStrByLocalDateTime(LocalDateTime.now(),DateTool.FORMAT_L6));
    }

    protected abstract void excute();
}
