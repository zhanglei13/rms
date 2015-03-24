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
@Table(name = "employee_workload_action_log", catalog = "rms_dev")
public class EmployeeWorkloadActionLog {

	// Fields

	private Integer id;
	private String itCode;
	private String workloadMonth;
	private String actionType;
	private String afterActionStatus;
	private String actionComments;
	private String operatorItcode;
	private Date operationDate;

	// Constructors

	/** default constructor */
	public EmployeeWorkloadActionLog() {
	}

	/** minimal constructor */
	public EmployeeWorkloadActionLog(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public EmployeeWorkloadActionLog(Integer id, String itCode,
			String workloadMonth, String actionType, String afterActionStatus,
			String actionComments, String operatorItcode, Date operationDate) {
		this.id = id;
		this.itCode = itCode;
		this.workloadMonth = workloadMonth;
		this.actionType = actionType;
		this.afterActionStatus = afterActionStatus;
		this.actionComments = actionComments;
		this.operatorItcode = operatorItcode;
		this.operationDate = operationDate;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "IT_CODE", length = 50)
	public String getItCode() {
		return this.itCode;
	}

	public void setItCode(String itCode) {
		this.itCode = itCode;
	}

	@Column(name = "WORKLOAD_MONTH", length = 6)
	public String getWorkloadMonth() {
		return this.workloadMonth;
	}

	public void setWorkloadMonth(String workloadMonth) {
		this.workloadMonth = workloadMonth;
	}

	@Column(name = "ACTION_TYPE", length = 1)
	public String getActionType() {
		return this.actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	@Column(name = "AFTER_ACTION_STATUS", length = 1)
	public String getAfterActionStatus() {
		return this.afterActionStatus;
	}

	public void setAfterActionStatus(String afterActionStatus) {
		this.afterActionStatus = afterActionStatus;
	}

	@Column(name = "ACTION_COMMENTS", length = 200)
	public String getActionComments() {
		return this.actionComments;
	}

	public void setActionComments(String actionComments) {
		this.actionComments = actionComments;
	}

	@Column(name = "OPERATOR_ITCODE", length = 50)
	public String getOperatorItcode() {
		return this.operatorItcode;
	}

	public void setOperatorItcode(String operatorItcode) {
		this.operatorItcode = operatorItcode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "OPERATION_DATE", length = 10)
	public Date getOperationDate() {
		return this.operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
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