package com.lenovo.rms.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "base_line", catalog = "rms_dev")
public class BaseLine {

	// Fields

	private Long id;
	private String projectNo;
	private String orgCode;
	private Long orgVersion;
	private Double baseLine;
	private String comments;
	private String updateItcode;
	private Timestamp updateDatetime;

	// Constructors

	/** default constructor */
	public BaseLine() {
	}

	/** minimal constructor */
	public BaseLine(Long id, String projectNo, String orgCode, Double baseLine) {
		this.id = id;
		this.projectNo = projectNo;
		this.orgCode = orgCode;
		this.baseLine = baseLine;
	}

	/** full constructor */
	public BaseLine(Long id, String projectNo, String orgCode, Long orgVersion,
			Double baseLine, String comments, String updateItcode,
			Timestamp updateDatetime) {
		this.id = id;
		this.projectNo = projectNo;
		this.orgCode = orgCode;
		this.orgVersion = orgVersion;
		this.baseLine = baseLine;
		this.comments = comments;
		this.updateItcode = updateItcode;
		this.updateDatetime = updateDatetime;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "project_no", nullable = false, length = 50)
	public String getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Column(name = "org_code", nullable = false, length = 50)
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "org_version")
	public Long getOrgVersion() {
		return this.orgVersion;
	}

	public void setOrgVersion(Long orgVersion) {
		this.orgVersion = orgVersion;
	}

	@Column(name = "base_line", nullable = false, precision = 9)
	public Double getBaseLine() {
		return this.baseLine;
	}

	public void setBaseLine(Double baseLine) {
		this.baseLine = baseLine;
	}

	@Column(name = "comments", length = 500)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "update_itcode", length = 100)
	public String getUpdateItcode() {
		return this.updateItcode;
	}

	public void setUpdateItcode(String updateItcode) {
		this.updateItcode = updateItcode;
	}

	@Column(name = "update_datetime", length = 19)
	public Timestamp getUpdateDatetime() {
		return this.updateDatetime;
	}

	public void setUpdateDatetime(Timestamp updateDatetime) {
		this.updateDatetime = updateDatetime;
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