package com.lenovo.rms.workload.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.rms.employee.dao.IAuthorityDao;
import com.lenovo.rms.employee.dao.IEmployeeDao;
import com.lenovo.rms.employee.dao.IOrgnizationDao;
import com.lenovo.rms.model.Employee;
import com.lenovo.rms.model.EmployeeWorkload;
import com.lenovo.rms.model.Project;
import com.lenovo.rms.project.dao.IProjectDao;
import com.lenovo.rms.workload.dao.IWorkloadDao;
import com.lenovo.rms.workload.model.ITLeaderViewTable;
import com.lenovo.rms.workload.model.ITLeaderViewTableItem;
import com.lenovo.rms.workload.model.TowerViewTable;
import com.lenovo.rms.workload.model.TowerViewTableItem;
import com.lenovo.rms.workload.service.ILeaderApproveService;

@Service("leaderApproveService")
public class LeaderApproveServiceImpl<IOrganization> implements
		ILeaderApproveService {

	// 开启日志
	protected static Logger logger = Logger
			.getLogger(LeaderApproveServiceImpl.class);
	@Autowired
	private IAuthorityDao authorityRoleDao;
	@Autowired
	private IEmployeeDao employeeDao;
	@Autowired
	private IProjectDao projectDao;
	@Autowired
	private IWorkloadDao workloadDao;
	@Autowired
	private IOrgnizationDao organizationDao;
	
	@Override
	public ITLeaderViewTable getITLeaderViewTable(String itCode, Date from,
			Date to) {
		// 1.根据itCode获取role,判断是否是ITleader;
		ITLeaderViewTable itLeaderTable = new ITLeaderViewTable();
		Employee employee = employeeDao.getByItCode(itCode);
		if (!authorityRoleDao.isITLeader(employee)) {
			logger.info(employee.getItCode() + " is not IT leader");
			return itLeaderTable;
		}
		// 2.如果是ITLeader,获取该ITleader下的project;
		List<String> projectNos = projectDao.getByItLeader(itCode);
		// 3.获取每一个project下的member，和该member在本月本项目上的workload;
		Map<String, Map<String, List<EmployeeWorkload>>> projectMemberWorkloads = new HashMap<String, Map<String, List<EmployeeWorkload>>>();
		for (String projectNo : projectNos) {
			List<String> members = projectDao.getProjectMembers(projectNo);
			if (members != null && !members.isEmpty()) {// 如果该项目下有member
				Map<String, List<EmployeeWorkload>> memberWorkloads = new HashMap<String, List<EmployeeWorkload>>();
				for (String member : members) {
					List<EmployeeWorkload> workloads = workloadDao
							.findWorkloads(employee, projectNo, from, to);
					memberWorkloads.put(member, workloads);
				}
				projectMemberWorkloads.put(projectNo, memberWorkloads);
			}
		}
		// 4.将数据转换成ITLeaderViewTable
		Set<String> projectNoSet = projectMemberWorkloads.keySet();
		List<ITLeaderViewTableItem> tableItems = new ArrayList<ITLeaderViewTableItem>();
		for (String projectNo : projectNoSet) {
			Project project = projectDao.getByProjectNo(projectNo);
			ITLeaderViewTableItem tableItem = new ITLeaderViewTableItem();
			tableItem.setProject(project);
			List<TowerViewTable> towers = new ArrayList<TowerViewTable>();
			Set<String> memberNoSet = projectMemberWorkloads.get(projectNo)
					.keySet();
			Map<String, List<Employee>> towerMembers = new HashMap<String, List<Employee>>();
			// 对members按org进行分类
			for (String memberNo : memberNoSet) {
				Employee member = employeeDao.getByItCode(memberNo);
				String orgName = organizationDao
						.getByItCode(member.getItCode()).getOrgName();
				if (towerMembers.containsKey(orgName)) {
					towerMembers.get(orgName).add(member);
				} else {
					List<Employee> m = new ArrayList<Employee>();
					m.add(member);
					towerMembers.put(orgName, m);
				}
			}
			Set<String> towerNameSet = towerMembers.keySet();
			for (String towerName : towerNameSet) {
				TowerViewTable tower = new TowerViewTable();
				tower.setName(towerName);
				List<TowerViewTableItem> towerItems = new ArrayList<TowerViewTableItem>();
				List<Employee> membersOfTower = towerMembers.get(towerName);
				int total = 0;
				for (Employee e : membersOfTower) {
					// 此处统计每个memeber的工作量
					List<EmployeeWorkload> workloads = projectMemberWorkloads
							.get(projectNo).get(e.getItCode());
					TowerViewTableItem item = statisticWorkloads(e, workloads);
					towerItems.add(item);
					total += item.getTotalActual();
				}
				tower.setItems(towerItems);
				tower.setPlanned(100);// set default 100
				tower.setUtilization(total / 100);
				towers.add(tower);
			}
			tableItem.setTowerViewTable(towers);
			tableItems.add(tableItem);
		}
		itLeaderTable.setItems(tableItems);
		return itLeaderTable;
	}

	private TowerViewTableItem statisticWorkloads(Employee employee,
			List<EmployeeWorkload> workloads) {
		TowerViewTableItem item = new TowerViewTableItem();
		item.setEmployeeName(employee.getNameEn());
		item.setEmployeeItCode(employee.getItCode());
		Map<String, Double> effortsByPhase = new HashMap<String, Double>();
		for (EmployeeWorkload workload : workloads) {
			if (effortsByPhase.containsKey(workload.getPhaseCode())) {
				double newEffort = effortsByPhase.get(workload.getPhaseCode())
						+ workload.getEffort();
				effortsByPhase.put(workload.getPhaseCode(), newEffort);
			} else {
				effortsByPhase.put(workload.getPhaseCode(),
						workload.getEffort());
			}
		}
		Map<String, Integer> actualMondayByPhase = new HashMap<String, Integer>();
		Set<String> phases = effortsByPhase.keySet();
		int count = 0;
		// 统计人天
		for (String phase : phases) {
			int monday = (int) (Math.ceil(effortsByPhase.get(phase) / 8));// 计算人天向上取整
			count += monday;
			actualMondayByPhase.put(phase, count);
		}
		item.setActualMondayByPhase(actualMondayByPhase);
		item.setTotalActual(count);
		if (!workloads.isEmpty())
			item.setStatus(workloads.get(0).getStatus());
		return item;
	}

}
