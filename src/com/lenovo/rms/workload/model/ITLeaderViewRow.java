/**   
 * 简述
 * <p>详细说明第一行<br>    
 * 详细说明第二行 
 * @date 2015年5月11日 上午9:17:36   
 * @author zhanglei   
 * @version V1.0   
 */
package com.lenovo.rms.workload.model;

/**
 * 简述
 * <p>
 * 详细说明第一行<br>
 * 详细说明第二行
 * 
 * @date 2015年5月11日 上午9:17:36
 * @author zhanglei
 * @version V1.0
 */
public class ITLeaderViewRow {
	private String projectName;
	private String towerName;
	private String planned;
	private String utilization;
	private String employeeName;
	private String employeeItCode;
	private String phase;
	private String actualMonday;
	private String status;

	public ITLeaderViewRow() {

	}

	public ITLeaderViewRow(ITLeaderViewRow row) {
		this.projectName = row.getProjectName();
		this.towerName = row.getTowerName();
		this.planned = row.getPlanned();
		this.utilization = row.getUtilization();
		this.employeeName = row.getEmployeeName();
		this.employeeItCode = row.getEmployeeItCode();
		this.phase = row.getPhase();
		this.actualMonday = row.getActualMonday();
		this.status = row.getStatus();
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getTowerName() {
		return towerName;
	}

	public void setTowerName(String towerName) {
		this.towerName = towerName;
	}

	public String getPlanned() {
		return planned;
	}

	public void setPlanned(String planned) {
		this.planned = planned;
	}

	public String getUtilization() {
		return utilization;
	}

	public void setUtilization(String utilization) {
		this.utilization = utilization;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeItCode() {
		return employeeItCode;
	}

	public void setEmployeeItCode(String employeeItCode) {
		this.employeeItCode = employeeItCode;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getActualMonday() {
		return actualMonday;
	}

	public void setActualMonday(String actualMonday) {
		this.actualMonday = actualMonday;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
