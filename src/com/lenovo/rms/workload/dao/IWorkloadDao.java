package com.lenovo.rms.workload.dao;

import java.util.Collection;

import com.lenovo.rms.common.dao.IHibernateBaseDao;
import com.lenovo.rms.model.Employee;
import com.lenovo.rms.model.EmployeeWorkload;

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
}
