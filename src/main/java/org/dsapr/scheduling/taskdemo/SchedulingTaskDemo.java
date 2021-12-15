package org.dsapr.scheduling.taskdemo;

/**
 * @author: chenyi.Wangwangwang
 * @date: 2021/12/15 18:44
 */
public class SchedulingTaskDemo {
    public void taskWithParams(String params) {
        System.out.println("执行待参数的定时任务..." + params);
    }
    public void taskWithoutParams() {
        System.out.println("执行不带参数的定时任务...");
    }
}
