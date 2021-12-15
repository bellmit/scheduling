package org.dsapr.scheduling.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * @author: chenyi.Wangwangwang
 * @date: 2021/12/15 14:32
 *
 * 一个对象代表一个定时任务
 */
@Entity(name = "t_sys_job")
public class SysJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobId;

    private String beanName;
    private String methodName;
    private String methodParams;
    // Cron 表达式
    private String cronExpression;
    // 定时任务状态
    private Integer status;
    private String remark;
    private Date createTime;
    private Date updateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysJob sysJob = (SysJob) o;
        return Objects.equals(beanName, sysJob.beanName)
                && Objects.equals(methodName, sysJob.methodName)
                && Objects.equals(methodParams, sysJob.methodParams)
                && Objects.equals(cronExpression, sysJob.cronExpression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beanName, methodName, methodParams, cronExpression);
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodParams() {
        return methodParams;
    }

    public void setMethodParams(String methodParams) {
        this.methodParams = methodParams;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
