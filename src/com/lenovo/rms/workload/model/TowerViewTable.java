package com.lenovo.rms.workload.model;

import java.util.List;

public class TowerViewTable {
	private String name;
	private List<TowerViewTableItem> items;
	private int planned;
	private double utilization;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<TowerViewTableItem> getItems() {
		return items;
	}
	public void setItems(List<TowerViewTableItem> items) {
		this.items = items;
	}
	public int getPlanned() {
		return planned;
	}
	public void setPlanned(int planned) {
		this.planned = planned;
	}
	public double getUtilization() {
		return utilization;
	}
	public void setUtilization(double utilization) {
		this.utilization = utilization;
	}
	

}
