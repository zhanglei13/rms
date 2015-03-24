package com.lenovo.rms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "authority_group_role_ref", catalog = "rms_dev")
public class AuthorityGroupRoleRef {

	// Fields

	private Long id;
	private String groupCode;
	private String roleCode;

	// Constructors

	/** default constructor */
	public AuthorityGroupRoleRef() {
	}

	/** minimal constructor */
	public AuthorityGroupRoleRef(Long id) {
		this.id = id;
	}

	/** full constructor */
	public AuthorityGroupRoleRef(Long id, String groupCode, String roleCode) {
		this.id = id;
		this.groupCode = groupCode;
		this.roleCode = roleCode;
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

	@Column(name = "GROUP_CODE", length = 10)
	public String getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	@Column(name = "ROLE_CODE", length = 10)
	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
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