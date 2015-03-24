package com.lenovo.rms.model;

import java.sql.Timestamp;
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
@Table(name = "employee_organization_history", catalog = "rms_dev")
public class EmployeeOrganizationHistory {

	// Fields

	private Long id;
	private String itCode;
	private String orgCode;
	private Long orgVersion;
	private Date startDate;
	private Date endDate;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public EmployeeOrganizationHistory() {
	}

	/** minimal constructor */
	public EmployeeOrganizationHistory(Long id, String itCode, String orgCode,
			Timestamp updateTime) {
		this.id = id;
		this.itCode = itCode;
		this.orgCode = orgCode;
		this.updateTime = updateTime;
	}

	/** full constructor */
	public EmployeeOrganizationHistory(Long id, String itCode, String orgCode,
			Long orgVersion, Date startDate, Date endDate, Timestamp updateTime) {
		this.id = id;
		this.itCode = itCode;
		this.orgCode = orgCode;
		this.orgVersion = orgVersion;
		this.startDate = startDate;
		this.endDate = endDate;
		this.updateTime = updateTime;
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

	@Column(name = "IT_CODE", nullable = false, length = 50)
	public String getItCode() {
		return this.itCode;
	}

	public void setItCode(String itCode) {
		this.itCode = itCode;
	}

	@Column(name = "Org_Code", nullable = false, length = 50)
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "ORG_VERSION")
	public Long getOrgVersion() {
		return this.orgVersion;
	}

	public void setOrgVersion(Long orgVersion) {
		this.orgVersion = orgVersion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Start_Date", length = 10)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "End_Date", length = 10)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "UPDATE_TIME", nullable = false, length = 19)
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
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