/**   
 * 简述
 * <p>详细说明第一行<br>    
 * 详细说明第二行 
 * @date 2015年4月1日 下午8:39:31   
 * @author zhanglei   
 * @version V1.0   
 */
package com.lenovo.rms.workload.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.lenovo.rms.common.util.DateUtils;
import com.lenovo.rms.model.Employee;
import com.lenovo.rms.model.EmployeeWorkload;
import com.lenovo.rms.workload.dao.IWorkloadDao;
import com.lenovo.rms.workload.model.WorkloadRow;
import com.lenovo.rms.workload.service.IWorkloadService;

/**
 * 简述
 * <p>
 * 详细说明第一行<br>
 * 详细说明第二行
 * 
 * @date 2015年4月1日 下午8:39:31
 * @author zhanglei
 * @version V1.0
 */
@Service("workloadService")
public class WorkloadServiceImpl implements IWorkloadService {

    // 开启日志
    protected static Logger logger = Logger.getLogger(WorkloadServiceImpl.class);

    @Autowired
    protected IWorkloadDao workloadDao;

    @Override
    public void saveWorkload(WorkloadRow workloadRow, String itCode) {
        for (int i = 0; i < 7; i++) {
            EmployeeWorkload workload = new EmployeeWorkload();
            workload.setItCode(itCode);
            workload.setEffort(workloadRow.getEffortPerWeek()[i]);
            workload.setWorkloadDate(workloadRow.getDatePerWeek()[i]);
            workload.setCreatorDate(new Date());
            workload.setProjectNo(workloadRow.getProjectNo());
            workload.setPhaseCode(workloadRow.getPhaseCode());
            workload.setCreator(workloadRow.getCreator());
            workloadDao.saveWorkload(workload);
        }
    }

    @Override
    public void saveWorkloads(List<WorkloadRow> workloadRows, String itCode) {
        for (WorkloadRow workloadRow : workloadRows)
            saveWorkload(workloadRow, itCode);
    }

    @Override
    public List<EmployeeWorkload> findWorkloads(Employee employee, Date from, Date to) {
        return workloadDao.findWorkloads(employee, from, to);
    }
    
    @Override
    public List<EmployeeWorkload> findWorkloads(Employee employee, Date from, Date to, String status) {
        return workloadDao.findWorkloads(employee, from, to, status);
    }

	@Override
	public List<WorkloadRow> listWorkloadRows(String itCode) {
	    List<WorkloadRow> rows = new ArrayList<>();
	    Employee employee = new Employee(itCode);
		Date today = new Date();  //获取当前时间
		
		Date[] dates = DateUtils.firstAndLastDate(today); //获取时间
		Date prevMonthFirstDay = dates[0];
		Date prevMonthFirstMon = dates[1];
		Date prevMonthLastDay = dates[2];
		Date prevMonthLastSun = dates[3];
		
		if(!DateUtils.isReachDeadLine(today)) { //判断是否是本月10号以后
		    List<EmployeeWorkload> rejectedWorkloads = 
		            this.findWorkloads(employee, prevMonthFirstDay, prevMonthLastDay, "2");   //是否存在驳回的workload
		    if(rejectedWorkloads.size() != 0) {   //存在的话上个月所有的workload打印出来修改
		        List<EmployeeWorkload> lastMonthWorkloads = this.findWorkloads(employee, prevMonthFirstMon, prevMonthLastSun);
		        this.addWorkloadRows(rows, lastMonthWorkloads);
		    }
		}
		
		List<EmployeeWorkload> savedWorkloads = this.findWorkloads(employee, DateUtils.getFirstDay(today), today);    //本月已经保存workload
		if(savedWorkloads.size()!=0)
		    this.addWorkloadRows(rows, savedWorkloads);
		else {
		    //获取上个月最后一个星期的workload
		}
		
		return null;
	}
	
	public void addWorkloadRows(List<WorkloadRow> rows, List<EmployeeWorkload> workloads) {
	    
	}
	
	public static void main(String[] args) throws ParseException {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-servlet.xml");
//        System.out.println("afasf");
//        IWorkloadService service = ctx.getBean(IWorkloadService.class);
//        Date date = new Date();
//        Date[] dates = new Date[7];
//        for (int i = 0; i < 7; i++)
//            dates[i] = date;
//        WorkloadRow row = new WorkloadRow("release", "dmm", "noPhrase", dates, new Double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
//                0.0, 0.0 }, "123", "zhanglei", "zhanglei");
//        service.saveWorkload(row, "zhanglei");
	    
    }
}
