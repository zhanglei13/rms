package com.lenovo.rms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "authority_role_resource_ref", catalog = "rms_dev")
public class AuthorityRoleResourceRef {

	// Fields

	private Long id;
	private String roleCode;
	private String resourceCode;

	// Constructors

	/** default constructor */
	public AuthorityRoleResourceRef() {
	}

	/** minimal constructor */
	public AuthorityRoleResourceRef(Long id) {
		this.id = id;
	}

	/** full constructor */
	public AuthorityRoleResourceRef(Long id, String roleCode,
			String resourceCode) {
		this.id = id;
		this.roleCode = roleCode;
		this.resourceCode = resourceCode;
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

	@Column(name = "ROLE_CODE", length = 10)
	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Column(name = "RESOURCE_CODE", length = 100)
	public String getResourceCode() {
		return this.resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
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