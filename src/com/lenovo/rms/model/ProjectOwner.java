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
@Table(name = "project_owner", catalog = "rms_dev")
public class ProjectOwner {

	// Fields

	private Long id;
	private String projectNo;
	private String orgCode;
	private Long orgVersion;
	private String projectOwner;
	private String projectItLeader;
	private String track;
	private Date validateFrom;
	private Date validateTo;
	private String comments;
	private Date createdDate;
	private Date updateDate;
	private String updatedBy;
	private String attrib1;
	private String attrib2;
	private Integer attrib3;

	// Constructors

	/** default constructor */
	public ProjectOwner() {
	}

	/** minimal constructor */
	public ProjectOwner(Long id) {
		this.id = id;
	}

	/** full constructor */
	public ProjectOwner(Long id, String projectNo, String orgCode,
			Long orgVersion, String projectOwner, String projectItLeader,
			String track, Date validateFrom, Date validateTo, String comments,
			Date createdDate, Date updateDate, String updatedBy,
			String attrib1, String attrib2, Integer attrib3) {
		this.id = id;
		this.projectNo = projectNo;
		this.orgCode = orgCode;
		this.orgVersion = orgVersion;
		this.projectOwner = projectOwner;
		this.projectItLeader = projectItLeader;
		this.track = track;
		this.validateFrom = validateFrom;
		this.validateTo = validateTo;
		this.comments = comments;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
		this.updatedBy = updatedBy;
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

	@Column(name = "PROJECT_OWNER", length = 50)
	public String getProjectOwner() {
		return this.projectOwner;
	}

	public void setProjectOwner(String projectOwner) {
		this.projectOwner = projectOwner;
	}

	@Column(name = "PROJECT_IT_LEADER", length = 50)
	public String getProjectItLeader() {
		return this.projectItLeader;
	}

	public void setProjectItLeader(String projectItLeader) {
		this.projectItLeader = projectItLeader;
	}

	@Column(name = "TRACK", length = 200)
	public String getTrack() {
		return this.track;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "VALIDATE_FROM", length = 10)
	public Date getValidateFrom() {
		return this.validateFrom;
	}

	public void setValidateFrom(Date validateFrom) {
		this.validateFrom = validateFrom;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "VALIDATE_TO", length = 10)
	public Date getValidateTo() {
		return this.validateTo;
	}

	public void setValidateTo(Date validateTo) {
		this.validateTo = validateTo;
	}

	@Column(name = "COMMENTS", length = 500)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE", length = 10)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_DATE", length = 10)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "UPDATED_BY", length = 50)
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
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