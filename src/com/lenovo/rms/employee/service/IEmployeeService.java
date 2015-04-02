package com.lenovo.rms.employee.service;
import com.lenovo.rms.model.Employee;

public interface IEmployeeService {
    
    /**   
    * 根据Itcode获取Employee信息
    * @date 2015年4月2日 下午4:18:00   
    * @author Eric   
    * @param itCode 用户的itCode
    * @return
    * Employee  相应itCode的用户信息
    */
    Employee getEmployeeByItCode(String itCode);
    

}
