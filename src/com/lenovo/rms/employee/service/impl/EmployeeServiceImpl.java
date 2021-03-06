package com.lenovo.rms.employee.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.rms.employee.dao.IEmployeeDao;
import com.lenovo.rms.employee.service.IEmployeeService;
import com.lenovo.rms.model.Employee;

@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService{

    // 开启日志
    protected static Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

    @Autowired
    protected IEmployeeDao employeeDao;
    
    @Override
    public Employee getEmployeeByItCode(String itCode) {
        return employeeDao.getByItCode(itCode);
    }  

}
