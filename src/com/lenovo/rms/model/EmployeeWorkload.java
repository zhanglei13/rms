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
@Table(name = "employee_workload", catalog = "rms_dev")
public class EmployeeWorkload {

	// Fields

	private Long id;
	private String projectNo;
	private String itCode;
	private Double effort;
	private String comments;
	private Date workloadDate;
	private String status;
	private String phaseCode;
	private String creator;
	private Date creatorDate;
	private String approver;
	private Date approverDate;

	// Constructors

	/** default constructor */
	public EmployeeWorkload() {
	}

	/** minimal constructor */
	public EmployeeWorkload(Long id) {
		this.id = id;
	}

	/** full constructor */
	public EmployeeWorkload(Long id, String projectNo, String itCode,
			Double effort, String comments, Date workloadDate, String status,
			String phaseCode, String creator, Date creatorDate,
			String approver, Date approverDate) {
		this.id = id;
		this.projectNo = projectNo;
		this.itCode = itCode;
		this.effort = effort;
		this.comments = comments;
		this.workloadDate = workloadDate;
		this.status = status;
		this.phaseCode = phaseCode;
		this.creator = creator;
		this.creatorDate = creatorDate;
		this.approver = approver;
		this.approverDate = approverDate;
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

	@Column(name = "PROJECT_NO", length = 150)
	public String getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Column(name = "IT_CODE", length = 150)
	public String getItCode() {
		return this.itCode;
	}

	public void setItCode(String itCode) {
		this.itCode = itCode;
	}

	@Column(name = "EFFORT", precision = 3, scale = 1)
	public Double getEffort() {
		return this.effort;
	}

	public void setEffort(Double effort) {
		this.effort = effort;
	}

	@Column(name = "COMMENTS", length = 600)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "WORKLOAD_DATE", length = 10)
	public Date getWorkloadDate() {
		return this.workloadDate;
	}

	public void setWorkloadDate(Date workloadDate) {
		this.workloadDate = workloadDate;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "PHASE_CODE", length = 150)
	public String getPhaseCode() {
		return this.phaseCode;
	}

	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
	}

	@Column(name = "CREATOR", length = 50)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATOR_DATE", length = 10)
	public Date getCreatorDate() {
		return this.creatorDate;
	}

	public void setCreatorDate(Date creatorDate) {
		this.creatorDate = creatorDate;
	}

	@Column(name = "APPROVER", length = 50)
	public String getApprover() {
		return this.approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "APPROVER_DATE", length = 10)
	public Date getApproverDate() {
		return this.approverDate;
	}

	public void setApproverDate(Date approverDate) {
		this.approverDate = approverDate;
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