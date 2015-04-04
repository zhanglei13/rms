package com.lenovo.rms.employee.dao;

import java.sql.Timestamp;

import com.lenovo.rms.model.Employee;

public interface IEmployeeDao {
    /**   
    * 根据ITCode获取Employee信息
    * @date 2015年3月31日 下午7:17:39   
    * @author Eric   
    * @param itCode 
    * @return Employee 所获取的对象  
    */
   Employee getByItCode(String itCode) ;
   
    /**
     * 添加用户的登录信息
     * @date 2015年4月4日 下午10:06:41
     * @author Eric
     * @param employee 登录的用户
     * @return void
     */
    void updateLoginInfo(Employee employee,String password,String ip,Timestamp time) ;
}
