
/**   
* 简述
* <p>详细说明第一行<br>    
* 详细说明第二行 
* @date 2015年4月1日 下午8:39:00   
* @author zhanglei   
* @version V1.0   
*/
package com.lenovo.rms.workload.service;

import java.util.Date;
import java.util.List;

import com.lenovo.rms.model.Employee;
import com.lenovo.rms.model.EmployeeWorkload;
import com.lenovo.rms.workload.model.WorkloadRow;

/**   
 * 简述
 * <p>详细说明第一行<br>    
 * 详细说明第二行 
 * @date 2015年4月1日 下午8:39:00   
 * @author zhanglei   
 * @version V1.0   
 */
public interface IWorkloadService {
	/**
	 * 
	* 简述
	* <p>详细说明第一行<br>    
	* 详细说明第二行 
	* @date 2015年4月1日 下午9:28:20   
	* @author zhanglei   
	* @param workloadRows
	* void
	 */
	
	public void saveWorkload(WorkloadRow workloadRow, String itCode);
	/**
	 * 
	* 表格所有输入数据保存数据库
	* @date 2015年4月1日 下午9:19:14   
	* @author zhanglei   
	* @param workloadRows
	* void
	 */
	public void saveWorkloads(List<WorkloadRow> workloadRows, String itCode);
	
	public boolean saveOrSubmitWorkloads(List<WorkloadRow> toDelete,List<WorkloadRow> toUpdate,List<WorkloadRow> toAdd,int optMonth,boolean submit,String itCode);

	public List<WorkloadRow> findWorkloads(Employee employee,Date from,Date to, String status);
		
	public List<WorkloadRow> findWorkloads(Employee employee, Date from, Date to);

	public List<WorkloadRow> findWorkloadsStatusNotEqual(Employee employee, Date from,
			Date to, String status);
	
	public List<WorkloadRow> listWorkloadRows(String itCode);
	
	public int getEarliestEditableMonth(Employee employee);
    
}