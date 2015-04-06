/**   
this.valuethis.valuethis.valueoptions.add * 简述
 * <p>详细说明第一行<br>    
 * 详细说明第二行 
 * @date 2015年4月1日 下午9:05:53   
 * @author zhanglei   
 * @version V1.0   
 */
package com.lenovo.rms.workload.model;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 简述
 * <p>
 * 详细说明第一行<br>
 * 详细说明第二行
 * 
 * @date 2015年4月1日 下午9:05:53
 * @author zhanglei
 * @version V1.0
 */
public class WorkloadRow {
	private String projectType;
	private String projectName;
	private String phaseCode;
	private Date[] datePerWeek;
	private Double[] effortPerWeek;
	private String projectNo;
	private String itCode;
	private String creator;

	public WorkloadRow() {
		datePerWeek = new Date[7];
		effortPerWeek = new Double[7];
	}

	public WorkloadRow(String projectType, String projectName,
			String phaseCode, Date[] datePerWeek, Double[] effortPerWeek,
			String projectNo, String itCode, String creator) {
		this.projectType = projectType;
		this.projectName = projectName;
		this.phaseCode = phaseCode;
		this.datePerWeek = datePerWeek;
		this.effortPerWeek = effortPerWeek;
		this.projectNo = projectNo;
		this.itCode = itCode;
		this.creator = creator;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPhaseCode() {
		return phaseCode;
	}

	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
	}

	public Date[] getDatePerWeek() {
		return datePerWeek;
	}

	public void setDatePerWeek(Date[] datePerWeek) {
		this.datePerWeek = datePerWeek;
	}

	public Double[] getEffortPerWeek() {
		return effortPerWeek;
	}

	public void setEffortPerWeek(Double[] effortPerWeek) {
		this.effortPerWeek = effortPerWeek;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getItCode() {
		return itCode;
	}

	public void setItCode(String itCode) {
		this.itCode = itCode;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
