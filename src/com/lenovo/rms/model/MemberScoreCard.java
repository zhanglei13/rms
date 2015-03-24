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
@Table(name = "member_scorecard", catalog = "rms_dev")
public class MemberScoreCard {

	// Fields

	private Integer id;
	private String projectNo;
	private String itCode;
	private String evalType;
	private Integer evalLevel;
	private Date createdDate;

	// Constructors

	/** default constructor */
	public MemberScoreCard() {
	}

	/** minimal constructor */
	public MemberScoreCard(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public MemberScoreCard(Integer id, String projectNo, String itCode,
			String evalType, Integer evalLevel, Date createdDate) {
		this.id = id;
		this.projectNo = projectNo;
		this.itCode = itCode;
		this.evalType = evalType;
		this.evalLevel = evalLevel;
		this.createdDate = createdDate;
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

	@Column(name = "PROJECT_NO", length = 50)
	public String getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Column(name = "IT_CODE", length = 20)
	public String getItCode() {
		return this.itCode;
	}

	public void setItCode(String itCode) {
		this.itCode = itCode;
	}

	@Column(name = "EVAL_TYPE", length = 50)
	public String getEvalType() {
		return this.evalType;
	}

	public void setEvalType(String evalType) {
		this.evalType = evalType;
	}

	@Column(name = "EVAL_LEVEL")
	public Integer getEvalLevel() {
		return this.evalLevel;
	}

	public void setEvalLevel(Integer evalLevel) {
		this.evalLevel = evalLevel;
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