package com.lenovo.rms.workload.dao.impl;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.lenovo.rms.common.dao.impl.HibernateBaseDaoImpl;
import com.lenovo.rms.model.Employee;
import com.lenovo.rms.model.EmployeeWorkload;
import com.lenovo.rms.model.UserLoginInfo;
import com.lenovo.rms.workload.dao.IWorkloadDao;
@Repository("workloadDao")
public class WorkloadDaoImpl  extends HibernateBaseDaoImpl<EmployeeWorkload, Long> implements IWorkloadDao  {

    @Override
    public void saveWorkload(EmployeeWorkload workload) {
        //设置状态为0，表示 saved
        workload.setStatus("0");
        save(workload);
    }

    @Override
    public void saveWorkloads(Collection<EmployeeWorkload> workloads) {
        for(EmployeeWorkload workload:workloads){
          //设置状态为0，表示 saved
            workload.setStatus("0");
        }
        saveAll(workloads);
    }
    
    @Override
    public List<EmployeeWorkload> findWorkloads(Employee employee, Date from, Date to, String status) {
        String hql = "from EmployeeWorkload w where w.itCode=:itCode and w.workloadDate>=:from and w.workloadDate<=:to and w.status=:status";
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("itCode", employee.getItCode());
        params.put("from", from);
        params.put("to", to);
        params.put("status", status);
        List<EmployeeWorkload> workloads = findHql(hql,params);
        return workloads;
    }
    
    @Override
    public List<EmployeeWorkload> findWorkloadsStatusNotEqual(Employee employee, Date from, Date to, String status) {
        String hql = "from EmployeeWorkload w where w.itCode=:itCode and w.workloadDate>=:from and w.workloadDate<=:to and w.status!=:status";
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("itCode", employee.getItCode());
        params.put("from", from);
        params.put("to", to);
        params.put("status", status);
        List<EmployeeWorkload> workloads = findHql(hql,params);
        return workloads;
    }
    
    @Override
    public List<EmployeeWorkload> findWorkloads(Employee employee, Date from, Date to) {
        String hql = "from EmployeeWorkload w where w.itCode=:itCode and w.workloadDate>=:from and w.workloadDate<=:to";
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("itCode", employee.getItCode());
        params.put("from", from);
        params.put("to", to);
        List<EmployeeWorkload> workloads = findHql(hql,params);
        return workloads;
    }
    
    
    @Override
    public void updateWorkloads(Collection<EmployeeWorkload> workloads) {
        for(EmployeeWorkload workload:workloads){
            updateWorkload(workload);
        }
    }
    
    private void updateWorkload(EmployeeWorkload workload){
        Criterion[]  criterions =new Criterion[3];
        criterions[0]= Restrictions.eq("itCode", workload.getItCode());
        criterions[1]= Restrictions.eq("projectNo", workload.getProjectNo());
        criterions[2]= Restrictions.eq("workloadDate", workload.getWorkloadDate());
        EmployeeWorkload  e= findUnique(EmployeeWorkload.class,criterions);
        workload.setId(e.getId());
        update(workload);
    }

    @Override
    public void deleteWorkloads(Collection<EmployeeWorkload> workloads) {
       for(EmployeeWorkload workload:workloads){
           deleteWorkload(workload);
       }
    }
    
    private void deleteWorkload(EmployeeWorkload workload){
        Criterion[]  criterions =new Criterion[3];
        criterions[0]= Restrictions.eq("itCode", workload.getItCode());
        criterions[1]= Restrictions.eq("projectNo", workload.getProjectNo());
        criterions[2]= Restrictions.eq("workloadDate", workload.getWorkloadDate());
        EmployeeWorkload  e= findUnique(EmployeeWorkload.class,criterions);
        workload.setId(e.getId());
        delete(workload);
        
    }
    
    public static void main(String[] args) throws ParseException{
        ApplicationContext   ctx = new ClassPathXmlApplicationContext("spring-servlet.xml");
        System.out.println("afasf");
        IWorkloadDao dao = ctx.getBean(WorkloadDaoImpl.class);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date from = dateFormat.parse("2015-03-10");
        Date to = dateFormat.parse("2015-03-10");
        Employee employee =new Employee();
        employee.setItCode("rjewell");
        List<EmployeeWorkload>  workloads = dao.findWorkloads(employee, from, to, "1");
        for(EmployeeWorkload w:workloads){
            System.out.println(w.getProjectNo());
        }
    }
   
}
