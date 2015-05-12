/**   
this.valuethis.valuethis.valueoptions.add * 简述
 * <p>详细说明第一行<br>    
 * 详细说明第二行 
 * @date 2015年4月1日 下午9:05:53   
 * @author zhanglei   
 * @version V1.0   
 */
package com.lenovo.rms.workload.model;

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
	private String dateRange;
	private String projectType;
	private String projectName;
	private String phaseCode;
	private String[] datePerWeek;
	private Double[] effortPerWeek;
	private String projectNo;
	private String itCode;
	private String creator;
	private String status;
	private String monStatus;
	private Long[] idPerWeek;
	private Boolean[] isHoliday;

	public WorkloadRow() {
		datePerWeek = new String[7];
		effortPerWeek = new Double[7];
		isHoliday = new Boolean[7];
	}

	public WorkloadRow(String dateRange, String projectType,
			String projectName, String phaseCode, String[] datePerWeek,
			Double[] effortPerWeek, String projectNo, String itCode,
			String creator, String status) {
		this.dateRange = dateRange;
		this.projectType = projectType;
		this.projectName = projectName;
		this.phaseCode = phaseCode;
		this.datePerWeek = datePerWeek;
		this.effortPerWeek = effortPerWeek;
		this.projectNo = projectNo;
		this.itCode = itCode;
		this.creator = creator;
		this.status = status;
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

	public String[] getDatePerWeek() {
		return datePerWeek;
	}

	public void setDatePerWeek(String[] datePerWeek) {
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

	public String getDateRange() {
		return dateRange;
	}

	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMonStatus() {
        return monStatus;
    }

    public void setMonStatus(String monStatus) {
        this.monStatus = monStatus;
    }

    public Long[] getIdPerWeek() {
        return idPerWeek;
    }

    public void setIdPerWeek(Long[] idPerWeek) {
        this.idPerWeek = idPerWeek;
    }

    public Boolean[] getIsHoliday() {
        return isHoliday;
    }

    public void setIsHoliday(Boolean[] isHoliday) {
        this.isHoliday = isHoliday;
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
