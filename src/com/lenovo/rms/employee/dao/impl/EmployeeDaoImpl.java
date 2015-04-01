package com.lenovo.rms.employee.dao.impl;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.lenovo.rms.common.dao.impl.HibernateBaseDaoImpl;
import com.lenovo.rms.employee.dao.IEmployeeDao;
import com.lenovo.rms.model.Employee;
@Repository("employeeDao")
public class EmployeeDaoImpl extends HibernateBaseDaoImpl<Employee,Long> implements IEmployeeDao{

    @Override
    public Employee getByItCode(String itCode) {
        Criterion condition = Restrictions.eq("itCode", itCode);
        return findUnique(condition);
    }
    
    public static void main(String[] args){
        ApplicationContext   ctx = new ClassPathXmlApplicationContext("spring-servlet.xml");
        System.out.println("afasf");
        IEmployeeDao dao = ctx.getBean(EmployeeDaoImpl.class);
        System.out.println(  dao.getByItCode("wujg"));
    }

}
