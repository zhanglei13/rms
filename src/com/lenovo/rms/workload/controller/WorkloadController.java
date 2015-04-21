package com.lenovo.rms.workload.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.lenovo.rms.common.util.JsonUtils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.rms.common.util.Constants;
import com.lenovo.rms.common.util.DateUtils;
import com.lenovo.rms.common.util.TimeUtils;
import com.lenovo.rms.model.Employee;
import com.lenovo.rms.model.Project;
import com.lenovo.rms.project.service.IProjectService;
import com.lenovo.rms.workload.model.WorkloadRow;
import com.lenovo.rms.workload.service.IWorkloadService;

/**
 * 简述
 * <p>
 * 详细说明第一行<br>
 * 详细说明第二行
 * 
 * @date 2015年4月3日 下午8:39:00
 * @author zhanglei
 * @version V1.0
 */

@Controller
public class WorkloadController {
	/**
	 * logger:创建日志logger
	 */
	protected static Logger logger = Logger.getLogger(WorkloadController.class);

	@Autowired
	private IWorkloadService workloadService;
	@Autowired
	private IProjectService projectService;

	@RequestMapping(value = "/workload", method = RequestMethod.GET)
	public String workload(HttpSession session, Model model) {
		Employee employee = (Employee) session
				.getAttribute(Constants.SESSION_USERINFO_KEY);
		model.addAttribute("itCode", employee.getItCode());
		model.addAttribute("name", employee.getNameEn());
		model.addAttribute("date", DateUtils.DateYMToString(new Date()));
		return "/workload";
	}

	@RequestMapping("/workload/list")
	@ResponseBody
	public List<WorkloadRow> listWorkloadRows(String itCode) {
		return workloadService.listWorkloadRows(itCode);
	}
	
	@RequestMapping(value = "/workload/save", method = RequestMethod.POST)
	@ResponseBody
	public void saveWorkloads(@RequestParam("workloadRows") String workloadRowsString,@RequestParam("itCode") String itCode) {
	     List<WorkloadRow> workloadRows = JsonUtils.jsonList2JavaList(workloadRowsString, WorkloadRow.class);
		 workloadService.saveWorkloads(workloadRows, itCode);

	}
	
	@RequestMapping(value = "/workload/saveOrSubmit", method = RequestMethod.POST)
	@ResponseBody
    public String saveWorkloads(@RequestParam("toUpdate") String toUpdateList,@RequestParam("toDelete") String toDeleteList,@RequestParam("toAdd") String toAddList,@RequestParam("optMonth") int optMonth,@RequestParam("submit") boolean submit,@RequestParam("itCode") String itCode) {
         List<WorkloadRow> toAdd = JsonUtils.jsonList2JavaList(toAddList, WorkloadRow.class);
         List<WorkloadRow> toDelete = JsonUtils.jsonList2JavaList(toDeleteList, WorkloadRow.class);
         List<WorkloadRow> toUpdate = JsonUtils.jsonList2JavaList(toUpdateList, WorkloadRow.class);
         if(workloadService.saveOrSubmitWorkloads(toDelete, toUpdate, toAdd, optMonth, submit, itCode))
             return "Successfully saved";
         else{
             return "Effor of each day must between 8 to 21";
         }
    }

	@RequestMapping(value="/workload/add",method = RequestMethod.GET)
	public String addWorkload(@RequestParam("dateRange") String dateRange,HttpSession session, Model model) {
	    List<WorkloadRow> workloadRows=null;
	    int earliestEditableMonth = 0;
	    List<Project> projects = projectService.getAllProjects();
	    if(!dateRange.equals("")){
	        //logger.info(dateRange);
	        String[] dates = dateRange.split("-");//date[0]代表起始日期，date[1]代表终止日期
	        Date from = DateUtils.parseString2(dates[0]);
	        Date to = DateUtils.parseString2(dates[1]);
	        Employee employee = (Employee) session.getAttribute(Constants.SESSION_USERINFO_KEY);
	        workloadRows= workloadService.findWorkloads(employee, from, to);
	    }
	    model.addAttribute("historyWorkloads", JsonUtils.javaList2JsonList(workloadRows));
	    model.addAttribute("earliestEditableMonth",earliestEditableMonth);
	    model.addAttribute("projects", projects);
	   
		return "/mdadd";
	}
}
