package com.lenovo.rms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "organization_relationship", catalog = "rms_dev")
public class OrganizationRelationship {

	// Fields

	private Long id;
	private String orgFrom;
	private String orgTo;
	private Long orgVersion;

	// Constructors

	/** default constructor */
	public OrganizationRelationship() {
	}

	/** minimal constructor */
	public OrganizationRelationship(Long id, String orgTo) {
		this.id = id;
		this.orgTo = orgTo;
	}

	/** full constructor */
	public OrganizationRelationship(Long id, String orgFrom, String orgTo,
			Long orgVersion) {
		this.id = id;
		this.orgFrom = orgFrom;
		this.orgTo = orgTo;
		this.orgVersion = orgVersion;
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

	@Column(name = "Org_From", length = 50)
	public String getOrgFrom() {
		return this.orgFrom;
	}

	public void setOrgFrom(String orgFrom) {
		this.orgFrom = orgFrom;
	}

	@Column(name = "Org_To", nullable = false, length = 50)
	public String getOrgTo() {
		return this.orgTo;
	}

	public void setOrgTo(String orgTo) {
		this.orgTo = orgTo;
	}

	@Column(name = "ORG_VERSION")
	public Long getOrgVersion() {
		return this.orgVersion;
	}

	public void setOrgVersion(Long orgVersion) {
		this.orgVersion = orgVersion;
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