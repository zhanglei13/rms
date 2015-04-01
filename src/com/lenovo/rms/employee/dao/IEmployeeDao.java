package com.lenovo.rms.employee.dao;

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
}
