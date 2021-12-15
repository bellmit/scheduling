package org.dsapr.scheduling.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 每一个定时任务对应一个子线程
 *
 * @author: chenyi.Wangwangwang
 * @date: 2021/12/15 13:29
 */
public class SchedulingRunnable implements Runnable{

    private static Logger logger = LoggerFactory.getLogger(SchedulingRunnable.class);

    private String beanName;
    private String methodName;
    private String params;
    private Object targetBean;
    private Method method;

    public SchedulingRunnable(String beanName, String methodName) {
        this(beanName, methodName, null);
    }

    public SchedulingRunnable(String beanName, String methodName, String params) {
        this.beanName = beanName;
        this.methodName = methodName;
        this.params = params;

        init();
    }

    private void init() {
        try {
            targetBean = SpringContextUtils.getBean(beanName);
            if (StringUtils.hasText(params)) {
                method = targetBean.getClass().getDeclaredMethod(methodName, String.class);
            }
            // 使方法可访问
            ReflectionUtils.makeAccessible(method);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        logger.info("定时任务开始执行 - bean:{},方法:{},参数:{}", beanName, method, params);
        long startTime = System.currentTimeMillis();
        // 如果方法有参数
        try {
            if (StringUtils.hasText(params)) {
                method.invoke(targetBean, params);
            } else {
                method.invoke(targetBean);
            }
        } catch (Exception e) {
            logger.error(String.format("定时任务执行异常 -bean: %s,方法: %s,参数: %s", beanName, methodName, params), e);
        }
        long endTime = System.currentTimeMillis();
        logger.info("定时任务制定结束 - bean:{},方法:{},参数:{},耗时:{}毫秒", beanName, method, params, (endTime - startTime));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchedulingRunnable that = (SchedulingRunnable) o;
        return beanName.equals(that.beanName)
                && methodName.equals(that.methodName)
                && params.equals(that.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beanName, methodName, params);
    }
}
