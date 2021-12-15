package org.dsapr.scheduling.config;

import java.util.concurrent.ScheduledFuture;

/**
 * @author: chenyi.Wangwangwang
 * @date: 2021/12/15 14:07
 */
public class ScheduledTask {
    volatile ScheduledFuture<?> future;

    public void cancel(){
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }
}
