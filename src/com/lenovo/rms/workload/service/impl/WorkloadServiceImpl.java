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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.lenovo.rms.common.util.DateUtils;
import com.lenovo.rms.model.Employee;
import com.lenovo.rms.model.EmployeeWorkload;
import com.lenovo.rms.model.Project;
import com.lenovo.rms.workload.dao.IProjectDao;
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

    @Autowired
    protected IProjectDao projectDao;

    @Override
    public void saveWorkload(WorkloadRow workloadRow, String itCode) {
        for (int i = 0; i < 7; i++) {
            double effort = workloadRow.getEffortPerWeek()[i];
            if (effort == 0)
                continue;
            EmployeeWorkload workload = new EmployeeWorkload();
            workload.setItCode(itCode);
            workload.setEffort(effort);
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
        Date today = new Date(); // 获取当前时间

        Date[] dates = DateUtils.firstAndLastDate(today); // 获取时间
        Date prevMonthFirstDay = dates[0];
        Date prevMonthFirstMon = dates[1];
        Date prevMonthLastDay = dates[2];
        Date prevMonthLastSun = dates[3];

        if (!DateUtils.isReachDeadLine(today)) { // 判断是否是本月10号以后
            List<EmployeeWorkload> rejectedWorkloads = findWorkloads(employee, prevMonthFirstDay, prevMonthLastDay, "2"); // 是否存在驳回的workload
            if (rejectedWorkloads.size() != 0) { // 存在的话上个月所有的workload打印出来修改
                List<EmployeeWorkload> lastMonthWorkloads = findWorkloads(employee, prevMonthFirstMon, prevMonthLastSun);
                addWorkloadRows(rows, lastMonthWorkloads, prevMonthFirstMon, prevMonthLastSun);
            }
        }

        Date firstDay = DateUtils.getFirstDay(today);
        List<EmployeeWorkload> savedWorkloads = findWorkloads(employee, firstDay, today); // 本月已经保存workload
        if (savedWorkloads.size() != 0)
            addWorkloadRows(rows, savedWorkloads, firstDay, today);
        else {
            // 获取上个月最后一个星期的workload
            Date[] week = DateUtils.getPrevMonthLastWeek(today);
            List<EmployeeWorkload> lastWeekWorkloads = findWorkloads(employee, week[0], week[1]);
            if (lastWeekWorkloads != null && lastWeekWorkloads.size() != 0)
                addWorkloadRows(rows, lastWeekWorkloads, week[0], week[1]);
        }
        return rows;
    }

    public void addWorkloadRows(List<WorkloadRow> rows, List<EmployeeWorkload> workloads, Date start, Date end) {
        int gap = (int) ((DateUtils.getDaysBetween(start, end) + 1) / 7); // 一共跨越的周数
        Map<String, WorkloadRow>[] weekRows = new HashMap[gap];
        for (int i = 0; i < gap; i++)
            weekRows[i] = new HashMap<>();

        for (EmployeeWorkload workload : workloads) {
            int week = (int) (DateUtils.getDaysBetween(start, end) / 7);
            int diff = (int) ((DateUtils.getDaysBetween(start, workload.getWorkloadDate()) + 1) % 7);

            String projectNo = workload.getProjectNo();
            if (!weekRows[week].containsKey(projectNo)) { // 判断该行是否存在
                WorkloadRow workloadRow = new WorkloadRow();
                workloadRow.setProjectNo(projectNo);
                workloadRow.setItCode(workload.getItCode());
                workloadRow.setCreator(workload.getCreator());
                workloadRow.setPhaseCode(workload.getPhaseCode());
                Project project = projectDao.getByProjectNo(projectNo);
                workloadRow.setProjectName(project.getProjectName());
                workloadRow.setProjectType(project.getProjectType());
                workloadRow.getDatePerWeek()[diff] = workload.getWorkloadDate();
                workloadRow.getEffortPerWeek()[diff] = workload.getEffort();
                weekRows[week].put(projectNo, workloadRow);
            } else {
                WorkloadRow workloadRow = weekRows[week].get(projectNo);
                workloadRow.getDatePerWeek()[diff] = workload.getWorkloadDate();
                workloadRow.getEffortPerWeek()[diff] = workload.getEffort();
            }
        }

        for (int i = 0; i < gap; i++) {
            for (WorkloadRow row : weekRows[i].values()) {
                rows.add(row);
            }
        }
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
