package com.lenovo.rms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "authority_resource", catalog = "rms_dev")
public class AuthorityResource {

	// Fields

	private Long id;
	private String resourceCode;
	private String resourceName;
	private String resourceUrl;
	private String resourceDesc;
	private Integer parentId;
	private String resourceType;
	private String resourceSequence;

	// Constructors

	/** default constructor */
	public AuthorityResource() {
	}

	/** minimal constructor */
	public AuthorityResource(Long id) {
		this.id = id;
	}

	/** full constructor */
	public AuthorityResource(Long id, String resourceCode, String resourceName,
			String resourceUrl, String resourceDesc, Integer parentId,
			String resourceType, String resourceSequence) {
		this.id = id;
		this.resourceCode = resourceCode;
		this.resourceName = resourceName;
		this.resourceUrl = resourceUrl;
		this.resourceDesc = resourceDesc;
		this.parentId = parentId;
		this.resourceType = resourceType;
		this.resourceSequence = resourceSequence;
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

	@Column(name = "RESOURCE_CODE", length = 100)
	public String getResourceCode() {
		return this.resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	@Column(name = "RESOURCE_NAME", length = 100)
	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@Column(name = "RESOURCE_URL", length = 200)
	public String getResourceUrl() {
		return this.resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	@Column(name = "RESOURCE_DESC", length = 300)
	public String getResourceDesc() {
		return this.resourceDesc;
	}

	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}

	@Column(name = "PARENT_ID")
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "RESOURCE_TYPE", length = 1)
	public String getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	@Column(name = "resource_sequence", length = 3)
	public String getResourceSequence() {
		return this.resourceSequence;
	}

	public void setResourceSequence(String resourceSequence) {
		this.resourceSequence = resourceSequence;
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