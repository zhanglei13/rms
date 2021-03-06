package com.lenovo.rms.workload.dao;

import java.util.Date;
import java.util.Collection;
import java.util.List;

import com.lenovo.rms.model.Employee;
import com.lenovo.rms.model.EmployeeWorkload;
import com.lenovo.rms.model.Project;

public interface IWorkloadDao{
    /**   
    * 保存一个workload
    * @date 2015年3月31日 下午3:49:55   
    * @author Eric   
    * @param workload 待保存的workload
    * @return void  
    */
    void saveWorkload(EmployeeWorkload workload);
    /**   
    * 批量保存workload
    * @date 2015年3月31日 下午3:50:43   
    * @author Eric   
    * @param workloads 待保存的workloads
    * @return void  
    */
    void saveWorkloads(Collection<EmployeeWorkload> workloads);
    
    void updateWorkloads(Collection<EmployeeWorkload> workloads);
    
    void deleteWorkloads(Collection<EmployeeWorkload> workloads);
    
    /**   
    * 根据时间和类型，查找某empoyee某一时间段内，某一状态的workloads
    * @date 2015年4月1日 上午11:01:08   
    * @author Eric   
    * @param employee 待查找的employee
    * @param from     查找的起始时间
    * @param to       查找的终止时间
    * @param status   状态
    * @return List<EmployeeWorkload> 查找到的workload列表  
    */
    List<EmployeeWorkload> findWorkloads(Employee employee,Date from,Date to, String status);
    
    List<EmployeeWorkload> findWorkloadsStatusNotEqual(Employee employee, Date from, Date to, String status);
    
    List<EmployeeWorkload> findWorkloadsStatusNotEqual(Employee employee, Project project, Date from, Date to, String status);
    /**   
    * 根据时间和类型，查找某empoyee某一时间段的workloads
    * @date 2015年4月1日 下午10:24:51   
    * @author Eric   
    * @param employee
    * @param from
    * @param to
    * @return
    * List<EmployeeWorkload>   查找到的workload列表  
    */
    List<EmployeeWorkload> findWorkloads(Employee employee,Date from,Date to);
    
    List<EmployeeWorkload> findWorkloads(Employee employee,String projectNo , Date from,Date to);
     
}
