package com.lenovo.rms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "country", catalog = "rms_dev")
public class Country {

	// Fields

	private Long id;
	private String countryName;
	private String countryCode;
	private String countryZone;

	// Constructors

	/** default constructor */
	public Country() {
	}

	/** minimal constructor */
	public Country(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Country(Long id, String countryName, String countryCode,
			String countryZone) {
		this.id = id;
		this.countryName = countryName;
		this.countryCode = countryCode;
		this.countryZone = countryZone;
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

	@Column(name = "COUNTRY_NAME", length = 10)
	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Column(name = "COUNTRY_CODE", length = 10)
	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Column(name = "COUNTRY_ZONE", length = 10)
	public String getCountryZone() {
		return this.countryZone;
	}

	public void setCountryZone(String countryZone) {
		this.countryZone = countryZone;
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