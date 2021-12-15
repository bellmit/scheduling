package org.dsapr.scheduling.service;

import org.dsapr.scheduling.dao.SysJobRepository;
import org.dsapr.scheduling.model.SysJob;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: chenyi.Wangwangwang
 * @date: 2021/12/15 17:15
 */
public class SysJobService {

    @Autowired
    SysJobRepository sysJobRepository;

    public List<SysJob> getSysJobByStatus(int status) {

        return sysJobRepository.findAllByStatus(status);
    }
}
