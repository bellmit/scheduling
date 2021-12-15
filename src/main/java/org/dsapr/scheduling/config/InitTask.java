package org.dsapr.scheduling.config;

import org.dsapr.scheduling.model.SysJob;
import org.dsapr.scheduling.service.SysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

/**
 * @author: chenyi.Wangwangwang
 * @date: 2021/12/15 17:04
 */
public class InitTask implements CommandLineRunner {
    @Autowired
    CronTaskRegistrar cronTaskRegistrar;
    @Autowired
    SysJobService sysJobService;

    @Override
    public void run(String... args) throws Exception {
        List<SysJob> list = sysJobService.getSysJobByStatus(1);
        for (SysJob sysJob : list) {
            // 执行没每一个定时任务
            cronTaskRegistrar.addCronTask(new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams()), sysJob.getCronExpression());

        }
    }
}
