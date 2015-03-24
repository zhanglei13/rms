package com.lenovo.rms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "authority_employee_group_ref", catalog = "rms_dev")
public class AuthorityEmployeeGroupRef {

	// Fields

	private Long id;
	private String itCode;
	private String groupCode;

	// Constructors

	/** default constructor */
	public AuthorityEmployeeGroupRef() {
	}

	/** minimal constructor */
	public AuthorityEmployeeGroupRef(Long id) {
		this.id = id;
	}

	/** full constructor */
	public AuthorityEmployeeGroupRef(Long id, String itCode, String groupCode) {
		this.id = id;
		this.itCode = itCode;
		this.groupCode = groupCode;
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

	@Column(name = "IT_CODE", length = 100)
	public String getItCode() {
		return this.itCode;
	}

	public void setItCode(String itCode) {
		this.itCode = itCode;
	}

	@Column(name = "GROUP_CODE", length = 10)
	public String getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
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