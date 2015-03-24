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
@Table(name = "holiday", catalog = "rms_dev")
public class Holiday {

	// Fields

	private Long id;
	private String holidayType;
	private Date date;
	private String year;
	private String status;
	private String comments;
	private String countryCode;

	// Constructors

	/** default constructor */
	public Holiday() {
	}

	/** minimal constructor */
	public Holiday(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Holiday(Long id, String holidayType, Date date, String year,
			String status, String comments, String countryCode) {
		this.id = id;
		this.holidayType = holidayType;
		this.date = date;
		this.year = year;
		this.status = status;
		this.comments = comments;
		this.countryCode = countryCode;
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

	@Column(name = "HOLIDAY_TYPE", length = 50)
	public String getHolidayType() {
		return this.holidayType;
	}

	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE", length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "YEAR", length = 10)
	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "COMMENTS", length = 50)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "COUNTRY_CODE", length = 10)
	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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