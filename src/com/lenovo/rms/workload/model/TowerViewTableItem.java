package com.lenovo.rms.workload.model;

import java.util.List;
import java.util.Map;

public class TowerViewTableItem {
	private String employeeName;
	private String employeeItCode;
	private Map<String,Integer> actualMondayByPhase;
	
	private int totalActual;
	
	private String status;
	
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

	
	public Map<String, Integer> getActualMondayByPhase() {
		return actualMondayByPhase;
	}

	public void setActualMondayByPhase(Map<String, Integer> actualMondayByPhase) {
		this.actualMondayByPhase = actualMondayByPhase;
	}
	
	
	public int getTotalActual() {
		return totalActual;
	}
	public void setTotalActual(int totalActual) {
		this.totalActual = totalActual;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
