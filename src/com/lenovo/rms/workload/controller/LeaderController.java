package com.lenovo.rms.workload.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.rms.common.util.Constants;
import com.lenovo.rms.common.util.JsonUtils;
import com.lenovo.rms.model.Employee;
import com.lenovo.rms.model.Project;
import com.lenovo.rms.workload.model.ITLeaderViewRow;
import com.lenovo.rms.workload.model.ITLeaderViewTable;
import com.lenovo.rms.workload.model.ITLeaderViewTableItem;
import com.lenovo.rms.workload.model.TowerViewTable;
import com.lenovo.rms.workload.model.TowerViewTableItem;
import com.lenovo.rms.workload.service.ILeaderApproveService;
import com.lenovo.rms.common.util.DateUtils;

@Controller
@RequestMapping("/leader")
public class LeaderController {
	/**
	 * logger:创建日志logger
	 */
	protected static Logger logger = Logger.getLogger(LeaderController.class);

	@Autowired
	private ILeaderApproveService leaderApproveService;
	
	@RequestMapping("/projects")
	@ResponseBody
	public String getAllProjects(HttpSession session) {
		Employee employee = (Employee) session
				.getAttribute(Constants.SESSION_USERINFO_KEY);
		Date now = new Date();
		ITLeaderViewTable itLeaderTable = leaderApproveService.getITLeaderViewTable(employee.getItCode(), DateUtils.prevMonthFirstDay(now), DateUtils.prevMonthLastDay(now));
		List<Project> projects = new ArrayList<>();
		for(ITLeaderViewTableItem item : itLeaderTable.getItems()) {
			projects.add(item.getProject());
		}
		return JsonUtils.javaList2JsonList(projects);
	}
	
	@RequestMapping("/getList")
	@ResponseBody
	public String listTower(String projectName,HttpSession session) {
		Employee employee = (Employee) session
				.getAttribute(Constants.SESSION_USERINFO_KEY);
		Date now = new Date();
		ITLeaderViewTable itLeaderTable = leaderApproveService.getITLeaderViewTable(employee.getItCode(), DateUtils.prevMonthFirstDay(now), DateUtils.prevMonthLastDay(now));

		List<ITLeaderViewRow> rows = new ArrayList<>();
		ITLeaderViewRow row = new ITLeaderViewRow();
		for(ITLeaderViewTableItem item : itLeaderTable.getItems()) {
			if(item.getProject().getProjectName().equals(projectName)) {
				row.setProjectName(item.getProject().getProjectName());
				for(TowerViewTable towerViewTable : item.getTowerViewTable()) {
					row.setTowerName(towerViewTable.getName());
					row.setPlanned(new Integer(towerViewTable.getPlanned()).toString());
					row.setUtilization(new Double(towerViewTable.getUtilization()).toString());
					
					for(TowerViewTableItem table : towerViewTable.getItems()) {
						row.setEmployeeItCode(table.getEmployeeName());
						row.setEmployeeName(table.getEmployeeName());
						
						for(Map.Entry<String,Integer> pair : table.getActualMondayByPhase().entrySet()) {
							row.setPhase(pair.getKey());
							row.setActualMonday(pair.getValue().toString());
							rows.add(new ITLeaderViewRow(row));
						}
					}
				}
			}
		}
		
		return JsonUtils.javaList2JsonList(rows);
	}
}
