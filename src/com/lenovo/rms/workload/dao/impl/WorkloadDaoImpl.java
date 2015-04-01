package com.lenovo.rms.workload.dao.impl;

import java.util.Collection;

import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.lenovo.rms.common.dao.impl.HibernateBaseDaoImpl;
import com.lenovo.rms.model.Employee;
import com.lenovo.rms.model.EmployeeWorkload;
import com.lenovo.rms.workload.dao.IWorkloadDao;
@Repository("workloadDao")
public class WorkloadDaoImpl  extends HibernateBaseDaoImpl<EmployeeWorkload, Long> implements IWorkloadDao  {

    @Override
    public void saveWorkload(EmployeeWorkload workload) {
        //设置状态为0，表示 saved
        workload.setStatus("0");
        save(workload);
    }

    @Override
    public void saveWorkloads(Collection<EmployeeWorkload> workloads) {
        for(EmployeeWorkload workload:workloads){
          //设置状态为0，表示 saved
            workload.setStatus("0");
        }
        saveAll(workloads);
    }
    
    public static void main(String[] args){
        ApplicationContext   ctx = new ClassPathXmlApplicationContext("spring-servlet.xml");
        System.out.println("afasf");
        IWorkloadDao dao = ctx.getBean(WorkloadDaoImpl.class);
        
    }
}
