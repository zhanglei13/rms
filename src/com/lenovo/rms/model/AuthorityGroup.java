package com.lenovo.rms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "authority_group", catalog = "rms_dev")
public class AuthorityGroup {

	// Fields

	private Long id;
	private String groupCode;
	private String groupName;
	private String groupDescription;

	// Constructors

	/** default constructor */
	public AuthorityGroup() {
	}

	/** minimal constructor */
	public AuthorityGroup(Long id) {
		this.id = id;
	}

	/** full constructor */
	public AuthorityGroup(Long id, String groupCode, String groupName,
			String groupDescription) {
		this.id = id;
		this.groupCode = groupCode;
		this.groupName = groupName;
		this.groupDescription = groupDescription;
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

	@Column(name = "GROUP_NAME", length = 100)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "GROUP_DESCRIPTION", length = 300)
	public String getGroupDescription() {
		return this.groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
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