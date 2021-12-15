package org.dsapr.scheduling.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 核心配置类
 *
 * @author: chenyi.Wangwangwang
 * @date: 2021/12/15 14:11
 */
public class CronTaskRegistrar implements DisposableBean {

    // 保存所有定时任务
    private final Map<Runnable, ScheduledTask> scheduledTasks = new ConcurrentHashMap<>(16);

    @Autowired
    TaskScheduler taskScheduler;

    public TaskScheduler taskScheduler() {
        return this.taskScheduler;
    }

    public void addCronTask(Runnable task, String cronExpression) {
        addCronTask(new CronTask(task, cronExpression));
    }

    private void addCronTask(CronTask cronTask) {
        if (cronTask != null) {
            Runnable runnable = cronTask.getRunnable();
            if(this.scheduledTasks.containsKey(runnable)) {
                // 说明要添加的定时任务已存在
                // 移除后再添加
                removeCronTask(runnable);
            }
            // 添加一个定时任务
            this.scheduledTasks.put(runnable, scheduledCronTask(cronTask));
        }
    }

    private ScheduledTask scheduledCronTask(CronTask cronTask) {
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.future = this.taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
        return scheduledTask;
    }

    private void removeCronTask(Runnable runnable) {
        // 从Map集合中移除
        ScheduledTask task = this.scheduledTasks.remove(runnable);
        // 取消正在执行的定时任务
        if (task != null) {
            task.cancel();
        }
    }

    @Override
    public void destroy() throws Exception {
        // 所有定时任务停止执行
        for (ScheduledTask task : this.scheduledTasks.values()) {
            task.cancel();
        }
        // 清空集合
        this.scheduledTasks.clear();
    }
}
