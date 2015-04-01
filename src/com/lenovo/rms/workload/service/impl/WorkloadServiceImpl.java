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
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    /*
     * 简述 <p>详细说明第一行<br> 详细说明第二行
     * 
     * @date 2015年4月1日 下午9:28:34
     * 
     * @author zhanglei
     * 
     * @param workloadRow
     * 
     * @see
     * com.lenovo.rms.workload.service.IWorkloadService#saveWorkload(com.lenovo
     * .rms.workload.model.WorkloadRow)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 100)
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

    /*
     * 简述 <p>详细说明第一行<br> 详细说明第二行
     * 
     * @date 2015年4月1日 下午9:20:51
     * 
     * @author zhanglei
     * 
     * @param workloadRows
     * 
     * @see
     * com.lenovo.rms.workload.service.IWorkloadService#saveWorkloads(java.util
     * .List)
     */
    @Override
    public void saveWorkloads(List<WorkloadRow> workloadRows, String itCode) {
        for (WorkloadRow workloadRow : workloadRows)
            saveWorkload(workloadRow, itCode);
    }

    /*
     * 简述 <p>详细说明第一行<br> 详细说明第二行
     * 
     * @date 2015年4月1日 下午9:20:51
     * 
     * @author zhanglei
     * 
     * @param employee
     * 
     * @param from
     * 
     * @param to
     * 
     * @param status
     * 
     * @return
     * 
     * @see
     * com.lenovo.rms.workload.service.IWorkloadService#findWorkloads(com.lenovo
     * .rms.model.Employee, java.util.Date, java.util.Date, java.lang.String)
     */
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true, timeout = 30)
    public List<EmployeeWorkload> findWorkloads(Employee employee, Date from, Date to, String status) {

        return null;
    }

    public static void main(String[] args) throws ParseException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-servlet.xml");
        System.out.println("afasf");
        IWorkloadService service = ctx.getBean(IWorkloadService.class);
        Date date = new Date();
        Date[] dates = new Date[7];
        for (int i = 0; i < 7; i++)
            dates[i] = date;
        WorkloadRow row = new WorkloadRow("release", "dmm", "noPhrase", dates, new Double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0 }, "123", "zhanglei", "zhanglei");
        service.saveWorkload(row, "zhanglei");
    }
}
