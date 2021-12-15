package org.dsapr.scheduling.dao;

import org.dsapr.scheduling.model.SysJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: chenyi.Wangwangwang
 * @date: 2021/12/15 17:03
 */
public interface SysJobRepository extends JpaRepository<SysJob,Integer> {
    List<SysJob> findAllByStatus(Integer status);
}
