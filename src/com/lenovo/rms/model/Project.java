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
@Table(name = "project", catalog = "rms_dev")
public class Project {

	// Fields

	private Long id;
	private String projectNo;
	private String projectName;
	private String projectLevel;
	private String projectType;
	private String comments;
	private String isClosed;
	private Integer orderNo;
	private Date createdDate;
	private Date updateDate;
	private String attrib1;
	private String attrib2;
	private Integer attrib3;

	// Constructors

	/** default constructor */
	public Project() {
	}

	/** minimal constructor */
	public Project(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Project(Long id, String projectNo, String projectName,
			String projectLevel, String projectType, String comments,
			String isClosed, Integer orderNo, Date createdDate,
			Date updateDate, String attrib1, String attrib2, Integer attrib3) {
		this.id = id;
		this.projectNo = projectNo;
		this.projectName = projectName;
		this.projectLevel = projectLevel;
		this.projectType = projectType;
		this.comments = comments;
		this.isClosed = isClosed;
		this.orderNo = orderNo;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
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

	@Column(name = "PROJECT_NAME", length = 100)
	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name = "PROJECT_LEVEL", length = 1)
	public String getProjectLevel() {
		return this.projectLevel;
	}

	public void setProjectLevel(String projectLevel) {
		this.projectLevel = projectLevel;
	}

	@Column(name = "PROJECT_TYPE", length = 50)
	public String getProjectType() {
		return this.projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Column(name = "COMMENTS", length = 300)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "IS_CLOSED", length = 1)
	public String getIsClosed() {
		return this.isClosed;
	}

	public void setIsClosed(String isClosed) {
		this.isClosed = isClosed;
	}

	@Column(name = "ORDER_NO")
	public Integer getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
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