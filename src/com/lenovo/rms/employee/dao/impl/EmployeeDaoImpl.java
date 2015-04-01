package com.lenovo.rms.employee.dao.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.lenovo.rms.common.dao.impl.HibernateBaseDaoImpl;
import com.lenovo.rms.employee.dao.IEmployeeDao;
import com.lenovo.rms.model.Employee;
import com.lenovo.rms.workload.dao.IWorkloadDao;
import com.lenovo.rms.workload.dao.impl.WorkloadDaoImpl;
@Repository("employeeDao")
public class EmployeeDaoImpl extends HibernateBaseDaoImpl<Employee,String> implements IEmployeeDao{

    @Override
    public Employee getByItCode(String itCode) {
        return get(itCode);
    }
    
    public static void main(String[] args){
        ApplicationContext   ctx = new ClassPathXmlApplicationContext("spring-servlet.xml");
        System.out.println("afasf");
        IWorkloadDao dao = ctx.getBean(WorkloadDaoImpl.class);
        
    }

}
