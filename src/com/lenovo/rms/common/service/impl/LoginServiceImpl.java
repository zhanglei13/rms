package com.lenovo.rms.common.service.impl;

import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.rms.common.service.ILoginService;
import com.lenovo.rms.common.util.Constants;
import com.lenovo.rms.common.util.TimeUtils;
import com.lenovo.rms.common.util.VerifyUtils;
import com.lenovo.rms.employee.dao.IEmployeeDao;
import com.lenovo.rms.model.Employee;
@Service("loginService")
public class LoginServiceImpl implements ILoginService{

    @Autowired
    private IEmployeeDao employeeDao;
    @Override
    public boolean login(String name, String password,String ip,HttpSession session) {
        boolean passed = VerifyUtils.verify(name, password);
        if(passed){
            Employee employee = employeeDao.getByItCode(name);
            //设置登录的用户信息
            if(employee!=null){
                session.setAttribute(Constants.SESSION_USERINFO_KEY, employee);
                Timestamp time= TimeUtils.nowTimestamp();
                employeeDao.updateLoginInfo(employee, password, ip, time);
                return true;
            }
        }
        return false;
    }

}
