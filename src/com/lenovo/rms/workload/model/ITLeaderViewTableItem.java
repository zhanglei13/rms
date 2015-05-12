package com.lenovo.rms.workload.model;

import java.util.List;

import com.lenovo.rms.model.Project;

public class ITLeaderViewTableItem {
	private Project project;
	private List<TowerViewTable> towerViewTables;
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public List<TowerViewTable> getTowerViewTable() {
		return towerViewTables;
	}
	public void setTowerViewTable(List<TowerViewTable> towerViewTables) {
		this.towerViewTables = towerViewTables;
	}
	
	

}
