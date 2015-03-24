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
@Table(name = "organization", catalog = "rms_dev")
public class Organization {

	// Fields

	private Long id;
	private String orgCode;
	private String orgName;
	private String description;
	private String orgLevel;
	private String orgStatus;
	private Date startDate;
	private Date endDate;
	private Long orgVersion;

	// Constructors

	/** default constructor */
	public Organization() {
	}

	/** minimal constructor */
	public Organization(Long id, String orgCode, String orgLevel) {
		this.id = id;
		this.orgCode = orgCode;
		this.orgLevel = orgLevel;
	}

	/** full constructor */
	public Organization(Long id, String orgCode, String orgName,
			String description, String orgLevel, String orgStatus,
			Date startDate, Date endDate, Long orgVersion) {
		this.id = id;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.description = description;
		this.orgLevel = orgLevel;
		this.orgStatus = orgStatus;
		this.startDate = startDate;
		this.endDate = endDate;
		this.orgVersion = orgVersion;
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

	@Column(name = "Org_Code", nullable = false, length = 50)
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "Org_Name", length = 100)
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "Description", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "Org_LEVEL", nullable = false, length = 1)
	public String getOrgLevel() {
		return this.orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

	@Column(name = "Org_Status", length = 1)
	public String getOrgStatus() {
		return this.orgStatus;
	}

	public void setOrgStatus(String orgStatus) {
		this.orgStatus = orgStatus;
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

	@Column(name = "ORG_VERSION")
	public Long getOrgVersion() {
		return this.orgVersion;
	}

	public void setOrgVersion(Long orgVersion) {
		this.orgVersion = orgVersion;
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