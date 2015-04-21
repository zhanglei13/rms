package com.lenovo.rms.workload.dao.impl;

import org.springframework.stereotype.Repository;

import com.lenovo.rms.common.dao.impl.HibernateBaseDaoImpl;
import com.lenovo.rms.model.EmployeeWorkload;
import com.lenovo.rms.workload.dao.ILeaderApproveDao;

@Repository("leaderApproveDao")
public class LeaderApproveDaoImpl extends HibernateBaseDaoImpl<EmployeeWorkload, Long> implements ILeaderApproveDao {
   
    public void updateWorkload() {
        
    }
}
