package com.lenovo.rms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "project_phase", catalog = "rms_dev")
public class ProjectPhase {

	// Fields

	private Long id;
	private String projectNo;
	private String phaseCode;
	private Date startDate;
	private Date endDate;
	private String planActualFlag;
	private Date updateDate;
	private Date createdDate;

	// Constructors

	/** default constructor */
	public ProjectPhase() {
	}

	/** minimal constructor */
	public ProjectPhase(Long id, String projectNo) {
		this.id = id;
		this.projectNo = projectNo;
	}

	/** full constructor */
	public ProjectPhase(Long id, String projectNo, String phaseCode,
			Date startDate, Date endDate, String planActualFlag,
			Date updateDate, Date createdDate) {
		this.id = id;
		this.projectNo = projectNo;
		this.phaseCode = phaseCode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.planActualFlag = planActualFlag;
		this.updateDate = updateDate;
		this.createdDate = createdDate;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PROJECT_NO", nullable = false, length = 50)
	public String getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Column(name = "PHASE_CODE", length = 50)
	public String getPhaseCode() {
		return this.phaseCode;
	}

	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE", length = 10)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE", length = 10)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "PLAN_ACTUAL_FLAG", length = 1)
	public String getPlanActualFlag() {
		return this.planActualFlag;
	}

	public void setPlanActualFlag(String planActualFlag) {
		this.planActualFlag = planActualFlag;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_DATE", length = 10)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE", length = 10)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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