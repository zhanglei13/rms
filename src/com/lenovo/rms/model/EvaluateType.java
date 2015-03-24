package com.lenovo.rms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "evaluate_type", catalog = "rms_dev")
public class EvaluateType {

	// Fields

	private Integer id;
	private String evalType;
	private String evalTypeName;

	// Constructors

	/** default constructor */
	public EvaluateType() {
	}

	/** minimal constructor */
	public EvaluateType(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public EvaluateType(Integer id, String evalType, String evalTypeName) {
		this.id = id;
		this.evalType = evalType;
		this.evalTypeName = evalTypeName;
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

	@Column(name = "EVAL_TYPE", length = 50)
	public String getEvalType() {
		return this.evalType;
	}

	public void setEvalType(String evalType) {
		this.evalType = evalType;
	}

	@Column(name = "EVAL_TYPE_NAME", length = 50)
	public String getEvalTypeName() {
		return this.evalTypeName;
	}

	public void setEvalTypeName(String evalTypeName) {
		this.evalTypeName = evalTypeName;
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