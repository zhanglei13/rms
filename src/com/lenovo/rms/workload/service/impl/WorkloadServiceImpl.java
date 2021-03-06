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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.lenovo.rms.common.util.DateUtils;
import com.lenovo.rms.model.Employee;
import com.lenovo.rms.model.EmployeeWorkload;
import com.lenovo.rms.model.Project;
import com.lenovo.rms.project.dao.IProjectDao;
import com.lenovo.rms.workload.dao.IHolidayDao;
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

    @Autowired
    protected IHolidayDao holidayDao;

    @Override
    public void saveWorkload(WorkloadRow workloadRow, String itCode) {
        for (int i = 0; i < 7; i++) {
            double effort = workloadRow.getEffortPerWeek()[i];
            if (effort == 0)
                continue;
            EmployeeWorkload workload = new EmployeeWorkload();
            workload.setItCode(itCode);
            workload.setEffort(effort);
            workload.setWorkloadDate(DateUtils.parseString((workloadRow.getDatePerWeek()[i])));
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
    public List<WorkloadRow> findWorkloads(Employee employee, Date from, Date to) {
        List<EmployeeWorkload> employeeWorkloads = workloadDao.findWorkloads(employee, from, to);
        List<WorkloadRow> workloadRowList = new ArrayList<WorkloadRow>();
        addWorkloadRows(workloadRowList, employeeWorkloads, from, to);
        return workloadRowList;
    }

    @Override
    public List<WorkloadRow> findWorkloads(Employee employee, Date from, Date to, String status) {
        List<EmployeeWorkload> employeeWorkloads = workloadDao.findWorkloads(employee, from, to, status);
        List<WorkloadRow> workloadRowList = new ArrayList<WorkloadRow>();
        addWorkloadRows(workloadRowList, employeeWorkloads, from, to);
        return workloadRowList;
    }

    @Override
    public List<WorkloadRow> findWorkloadsStatusNotEqual(Employee employee, Date from, Date to, String status) {
        List<EmployeeWorkload> employeeWorkloads = workloadDao.findWorkloadsStatusNotEqual(employee, from, to, status);
        List<WorkloadRow> workloadRowList = new ArrayList<WorkloadRow>();
        addWorkloadRows(workloadRowList, employeeWorkloads, from, to);
        return workloadRowList;
    }

    @Override
    public int getEarliestEditableMonth(Employee employee) {
        Date today = new Date();
        int month = DateUtils.getMonth(today);
        Date prevMonthFirstDay = DateUtils.prevMonthFirstDay(today);
        Date prevMonthLastDay = DateUtils.prevMonthLastDay(today);
        List<EmployeeWorkload> notApprovedWorkloads = workloadDao.findWorkloadsStatusNotEqual(employee,
                prevMonthFirstDay, prevMonthLastDay, "3");
        return notApprovedWorkloads.size() == 0 ? month : month - 1;
    }

    @Override
    public List<WorkloadRow> listWorkloadRows(String itCode) {
        List<WorkloadRow> rows = new ArrayList<>();
        Employee employee = new Employee(itCode);
        Date today = new Date(); // 获取当前时间
        Date[] dates = DateUtils.firstAndLastDate(today); // 获取时间
        Date prevMonthFirstDay = dates[0];
        Date prevMonthFirstMon = dates[1];

        List<EmployeeWorkload> notApprovedWorkloads = workloadDao.findWorkloadsStatusNotEqual(employee,
                prevMonthFirstDay, today, "3");
        if (notApprovedWorkloads.size() != 0) {
            rows = findWorkloads(employee, prevMonthFirstMon, DateUtils.currentWeekSun(today));
        }

        return rows;	
    }

    private void addWorkloadRows(List<WorkloadRow> rows, List<EmployeeWorkload> workloads, Date start, Date end) {
        int gap = (int) ((DateUtils.getDaysBetween(start, end) + 1) / 7); // 一共跨越的周数
        if (gap == 0)
            gap = 1;
        Map<String, WorkloadRow>[] weekRows = new HashMap[gap];
        for (int i = 0; i < gap; i++)
            weekRows[i] = new HashMap<>();

        for (EmployeeWorkload workload : workloads) {
            int week = (int) ((DateUtils.getDaysBetween(start, workload.getWorkloadDate())) / 7);
            int diff = (int) ((DateUtils.getDaysBetween(start, workload.getWorkloadDate()) + 1) % 7);
            
            String projectNo = workload.getProjectNo();
            if (!weekRows[week].containsKey(projectNo)) { // 判断该行是否存在
                WorkloadRow workloadRow = new WorkloadRow();
                Date[] range = DateUtils.numWeeksRange(start, week);
                workloadRow.setDateRange(DateUtils.DateYMDToString(range[0]) + "-"
                        + DateUtils.DateYMDToString(range[1]));
                workloadRow.setProjectNo(projectNo);
                workloadRow.setItCode(workload.getItCode());
                workloadRow.setCreator(workload.getCreator());
                workloadRow.setStatus(workload.getStatus());
                workloadRow.setPhaseCode(workload.getPhaseCode());
                Project project = projectDao.getByProjectNo(projectNo);
                if (project != null) {
                    workloadRow.setProjectName(project.getProjectName());
                    workloadRow.setProjectType(project.getProjectType());
                }
                workloadRow.getDatePerWeek()[diff] = DateUtils.formatDate(workload.getWorkloadDate());
                workloadRow.getEffortPerWeek()[diff] = workload.getEffort();
                weekRows[week].put(projectNo, workloadRow);
            } else {
                WorkloadRow workloadRow = weekRows[week].get(projectNo);
                workloadRow.getDatePerWeek()[diff] = DateUtils.formatDate(workload.getWorkloadDate());
                workloadRow.getEffortPerWeek()[diff] = workload.getEffort();
            }
        }

        Set<String> holidays = holidayDao.listHolidaysByYear(DateUtils.getYearByDate(start));
        for (int i = 0; i < gap; i++) {
            for (WorkloadRow row : weekRows[i].values()) {
                for (int j = 0; j < row.getDatePerWeek().length; j++) {
                    row.getIsHoliday()[j] = holidays.contains(row.getDatePerWeek()[j]);
                }

                rows.add(row);
            }
        }
    }

    public static void main(String[] args) throws ParseException {
        /*
         * ApplicationContext ctx = new
         * ClassPathXmlApplicationContext("spring-servlet.xml");
         * System.out.println("afasf"); IWorkloadService service =
         * ctx.getBean(IWorkloadService.class); Date date = new Date(); Date[]
         * dates = new Date[7]; for (int i = 0; i < 7; i++) dates[i] = date;
         * WorkloadRow row = new WorkloadRow("release", "dmm", "noPhrase",
         * dates, new Double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, "123",
         * "zhanglei", "zhanglei"); service.saveWorkload(row, "zhanglei");
         */
    }

    @Override
    public boolean saveOrSubmitWorkloads(List<WorkloadRow> toDelete, List<WorkloadRow> toUpdate,
            List<WorkloadRow> toAdd, int optMonth, boolean submit, String itCode) {
        if (submit) {
            return saveWorkloads(toDelete, toUpdate, toAdd, optMonth, "1", itCode);
        } else {
            return saveWorkloads(toDelete, toUpdate, toAdd, optMonth, "0", itCode);
        }

    }

    @SuppressWarnings("deprecation")
    protected boolean saveWorkloads(List<WorkloadRow> toDelete, List<WorkloadRow> toUpdate, List<WorkloadRow> toAdd,
            int optMonth, String status, String itCode) {
        // 选出操作月的要删除的workload
        List<EmployeeWorkload> toDelList = new ArrayList<EmployeeWorkload>();
        for (WorkloadRow del : toDelete) {
            for (int i = 0; i < del.getDatePerWeek().length; i++) {
                Date date = DateUtils.parseString(del.getDatePerWeek()[i]);
                if (date.getMonth() >= optMonth) {
                    EmployeeWorkload workload = new EmployeeWorkload();
                    workload.setItCode(itCode);
                    workload.setWorkloadDate(DateUtils.parseString((del.getDatePerWeek()[i])));
                    workload.setCreatorDate(new Date());
                    workload.setProjectNo(del.getProjectNo());
                    workload.setPhaseCode(del.getPhaseCode());
                    workload.setCreator(del.getCreator());
                    toDelList.add(workload);
                }
            }
        }

        double[] effortPerDay = new double[7];

        // 选出操作月的要更新的workload
        List<EmployeeWorkload> toUpdateList = new ArrayList<EmployeeWorkload>();
        for (WorkloadRow upd : toUpdate) {
            for (int i = 0; i < upd.getDatePerWeek().length; i++) {
                Date date = DateUtils.parseString(upd.getDatePerWeek()[i]);
                double effort = upd.getEffortPerWeek()[i];
                effortPerDay[i] += effort;
                if (date.getMonth() >= optMonth && effort != 0) {
                    EmployeeWorkload workload = new EmployeeWorkload();
                    workload.setItCode(itCode);
                    workload.setEffort(effort);
                    workload.setWorkloadDate(DateUtils.parseString((upd.getDatePerWeek()[i])));
                    workload.setCreatorDate(new Date());
                    workload.setProjectNo(upd.getProjectNo());
                    workload.setPhaseCode(upd.getPhaseCode());
                    workload.setCreator(upd.getCreator());
                    workload.setStatus(status);
                    toUpdateList.add(workload);
                }
            }
        }

        // 选出操作月的要添加的workload
        List<EmployeeWorkload> toAddList = new ArrayList<EmployeeWorkload>();
        for (WorkloadRow add : toAdd) {
            for (int i = 0; i < add.getDatePerWeek().length; i++) {
                Date date = DateUtils.parseString(add.getDatePerWeek()[i]);
                double effort = add.getEffortPerWeek()[i];
                effortPerDay[i] += effort;
                if (date.getMonth() >= optMonth && effort != 0) {
                    EmployeeWorkload workload = new EmployeeWorkload();
                    workload.setItCode(itCode);
                    workload.setEffort(effort);
                    workload.setWorkloadDate(DateUtils.parseString((add.getDatePerWeek()[i])));
                    workload.setCreatorDate(new Date());
                    workload.setProjectNo(add.getProjectNo());
                    workload.setPhaseCode(add.getPhaseCode());
                    workload.setCreator(add.getCreator());
                    workload.setStatus(status);
                    toAddList.add(workload);
                }
            }
        }
        // 校验工作量
        logger.info("检查工作量");
        String[] datePerWeek = new String[7];

        if (toAdd != null && !toAdd.isEmpty())
            datePerWeek = toAdd.get(0).getDatePerWeek();
        else if (toUpdate != null && !toUpdate.isEmpty())
            datePerWeek = toUpdate.get(0).getDatePerWeek();
        else
            // 如果没有新加的且没有更新的，则操作失败
            return false;

        for (int i = 0; i < 7; i++) {
            System.out.println(effortPerDay[i]);
        }
        for (int i = 0; i < datePerWeek.length; i++) {
            Date date = DateUtils.parseString(datePerWeek[i]);
            if (date.getMonth() >= optMonth) {
                // System.out.println(effortPerDay[i]);
                if (effortPerDay[i] < 8.0 || effortPerDay[i] > 21.0) {
                    logger.info("工作量不足");
                    return false;
                }

            }
        }

        workloadDao.saveWorkloads(toAddList);
        workloadDao.updateWorkloads(toUpdateList);
        workloadDao.deleteWorkloads(toDelList);
        return true;
    }

}
