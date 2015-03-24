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
@Table(name = "project_member", catalog = "rms_dev")
public class ProjectMember {

	// Fields

	private Long id;
	private String itCode;
	private String projectNo;
	private String orgCode;
	private Long orgVersion;
	private String updatedBy;
	private Date updateDate;
	private String startDate;
	private String endDate;
	private String roleCode;
	private Date createdDate;
	private String comments;
	private String attrib1;
	private String attrib2;
	private Integer attrib3;

	// Constructors

	/** default constructor */
	public ProjectMember() {
	}

	/** minimal constructor */
	public ProjectMember(Long id) {
		this.id = id;
	}

	/** full constructor */
	public ProjectMember(Long id, String itCode, String projectNo,
			String orgCode, Long orgVersion, String updatedBy, Date updateDate,
			String startDate, String endDate, String roleCode,
			Date createdDate, String comments, String attrib1, String attrib2,
			Integer attrib3) {
		this.id = id;
		this.itCode = itCode;
		this.projectNo = projectNo;
		this.orgCode = orgCode;
		this.orgVersion = orgVersion;
		this.updatedBy = updatedBy;
		this.updateDate = updateDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.roleCode = roleCode;
		this.createdDate = createdDate;
		this.comments = comments;
		this.attrib1 = attrib1;
		this.attrib2 = attrib2;
		this.attrib3 = attrib3;
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

	@Column(name = "IT_CODE", length = 50)
	public String getItCode() {
		return this.itCode;
	}

	public void setItCode(String itCode) {
		this.itCode = itCode;
	}

	@Column(name = "PROJECT_NO", length = 50)
	public String getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Column(name = "ORG_CODE", length = 50)
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

	@Column(name = "UPDATED_BY", length = 50)
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_DATE", length = 10)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "START_DATE", length = 10)
	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Column(name = "END_DATE", length = 10)
	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Column(name = "Role_CODE", length = 1)
	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE", length = 10)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "COMMENTS", length = 500)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "ATTRIB1", length = 20)
	public String getAttrib1() {
		return this.attrib1;
	}

	public void setAttrib1(String attrib1) {
		this.attrib1 = attrib1;
	}

	@Column(name = "ATTRIB2", length = 20)
	public String getAttrib2() {
		return this.attrib2;
	}

	public void setAttrib2(String attrib2) {
		this.attrib2 = attrib2;
	}

	@Column(name = "ATTRIB3")
	public Integer getAttrib3() {
		return this.attrib3;
	}

	public void setAttrib3(Integer attrib3) {
		this.attrib3 = attrib3;
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