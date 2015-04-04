package com.lenovo.rms.employee.dao.impl;

import java.sql.Timestamp;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.lenovo.rms.common.dao.impl.HibernateBaseDaoImpl;
import com.lenovo.rms.common.util.MD5Utils;
import com.lenovo.rms.employee.dao.IEmployeeDao;
import com.lenovo.rms.model.Employee;
import com.lenovo.rms.model.UserLoginInfo;
@Repository("employeeDao")
public class EmployeeDaoImpl extends HibernateBaseDaoImpl<Employee,Long> implements IEmployeeDao{

    @Override
    public Employee getByItCode(String itCode) {
        Criterion condition = Restrictions.eq("itCode", itCode);
        return findUnique(condition);
    }
    
 
    @Override
    public void updateLoginInfo(Employee employee,String password,String ip,Timestamp time) {
        Criterion criterion = Restrictions.eq("loginName", employee.getItCode());
        UserLoginInfo loginInfo = findUnique(UserLoginInfo.class,criterion);
        if(loginInfo==null) loginInfo =new UserLoginInfo();
        loginInfo.setLoginName(employee.getItCode());
        loginInfo.setLoginPassword(MD5Utils.encoding(password));
        loginInfo.setLoginType("0");//此处login type 不太明确 ;
        loginInfo.setLastLoginIp(ip);
        loginInfo.setLastLoginTime(time); 
        saveOrUpdate(UserLoginInfo.class,loginInfo);
    }
    
    public static void main(String[] args){
        ApplicationContext   ctx = new ClassPathXmlApplicationContext("spring-servlet.xml");
        System.out.println("afasf");
        IEmployeeDao dao = ctx.getBean(EmployeeDaoImpl.class);
        System.out.println(  dao.getByItCode("wujg"));
    }

}
