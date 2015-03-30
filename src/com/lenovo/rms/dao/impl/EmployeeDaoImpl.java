package com.lenovo.rms.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.lenovo.rms.common.dao.impl.HibernateBaseDaoImpl;
import com.lenovo.rms.dao.IEmployeeDao;
import com.lenovo.rms.model.Employee;
@Repository("employeeDao")
public class EmployeeDaoImpl  extends HibernateBaseDaoImpl<Employee, Long> implements IEmployeeDao  {

    public Employee findEmployeeByName(String name){
       /* Criterion[] condition = new Criterion[]{ 
                  Restrictions.eq("username", name)
                };*/
        return findUnique(Employee.class, Restrictions.eq("nameEn", name));
    }

    public static void main(String[] args){
        ApplicationContext   ctx = new ClassPathXmlApplicationContext("spring-servlet.xml");
        System.out.println("afasf");
        IEmployeeDao dao = ctx.getBean(EmployeeDaoImpl.class);
        Employee e=dao.findEmployeeByName("Eric");
        System.out.println(e.getEmpId());
    }
    
}
