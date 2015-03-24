package com.lenovo.rms.model;

import java.sql.Timestamp;
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
@Table(name = "employee_change_history", catalog = "rms_dev")
public class EmployeeChangeHistory {

	// Fields

	private Long id;
	private String itCode;
	private String orgCode;
	private Long orgVersion;
	private String ladder;
	private String band;
	private String position;
	private String linemanagerItcode;
	private String operatiorItcode;
	private Timestamp operationDate;
	private String changeReason;
	private Date startDate;
	private Date endDate;
	private String description;
	private String nameCh;
	private String nameEn;
	private String sex;
	private Date birthDate;
	private String graduatedSchool;
	private String major;
	private String degree;
	private String baseCode;
	private String staffNo;
	private Date lenovoStartDate;
	private Date gadStartDate;
	private Date demissionDate;
	private String administrationLevel;
	private String status;

	// Constructors

	/** default constructor */
	public EmployeeChangeHistory() {
	}

	/** minimal constructor */
	public EmployeeChangeHistory(Long id, String itCode, String orgCode,
			String ladder, String band, String operatiorItcode,
			Timestamp operationDate, String changeReason, Date startDate) {
		this.id = id;
		this.itCode = itCode;
		this.orgCode = orgCode;
		this.ladder = ladder;
		this.band = band;
		this.operatiorItcode = operatiorItcode;
		this.operationDate = operationDate;
		this.changeReason = changeReason;
		this.startDate = startDate;
	}

	/** full constructor */
	public EmployeeChangeHistory(Long id, String itCode, String orgCode,
			Long orgVersion, String ladder, String band, String position,
			String linemanagerItcode, String operatiorItcode,
			Timestamp operationDate, String changeReason, Date startDate,
			Date endDate, String description, String nameCh, String nameEn,
			String sex, Date birthDate, String graduatedSchool, String major,
			String degree, String baseCode, String staffNo,
			Date lenovoStartDate, Date gadStartDate, Date demissionDate,
			String administrationLevel, String status) {
		this.id = id;
		this.itCode = itCode;
		this.orgCode = orgCode;
		this.orgVersion = orgVersion;
		this.ladder = ladder;
		this.band = band;
		this.position = position;
		this.linemanagerItcode = linemanagerItcode;
		this.operatiorItcode = operatiorItcode;
		this.operationDate = operationDate;
		this.changeReason = changeReason;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.nameCh = nameCh;
		this.nameEn = nameEn;
		this.sex = sex;
		this.birthDate = birthDate;
		this.graduatedSchool = graduatedSchool;
		this.major = major;
		this.degree = degree;
		this.baseCode = baseCode;
		this.staffNo = staffNo;
		this.lenovoStartDate = lenovoStartDate;
		this.gadStartDate = gadStartDate;
		this.demissionDate = demissionDate;
		this.administrationLevel = administrationLevel;
		this.status = status;
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

	@Column(name = "IT_CODE", nullable = false, length = 50)
	public String getItCode() {
		return this.itCode;
	}

	public void setItCode(String itCode) {
		this.itCode = itCode;
	}

	@Column(name = "ORG_CODE", nullable = false, length = 50)
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "ORG_VERSION")
	public Long getOrgVersion() {
		return this.orgVersion;
	}

	public void setOrgVersion(Long orgVersion) {
		this.orgVersion = orgVersion;
	}

	@Column(name = "LADDER", nullable = false, length = 20)
	public String getLadder() {
		return this.ladder;
	}

	public void setLadder(String ladder) {
		this.ladder = ladder;
	}

	@Column(name = "BAND", nullable = false, length = 20)
	public String getBand() {
		return this.band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	@Column(name = "POSITION", length = 50)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "LINEMANAGER_ITCODE", length = 100)
	public String getLinemanagerItcode() {
		return this.linemanagerItcode;
	}

	public void setLinemanagerItcode(String linemanagerItcode) {
		this.linemanagerItcode = linemanagerItcode;
	}

	@Column(name = "OPERATIOR_ITCODE", nullable = false, length = 50)
	public String getOperatiorItcode() {
		return this.operatiorItcode;
	}

	public void setOperatiorItcode(String operatiorItcode) {
		this.operatiorItcode = operatiorItcode;
	}

	@Column(name = "OPERATION_DATE", nullable = false, length = 19)
	public Timestamp getOperationDate() {
		return this.operationDate;
	}

	public void setOperationDate(Timestamp operationDate) {
		this.operationDate = operationDate;
	}

	@Column(name = "CHANGE_REASON", nullable = false, length = 1)
	public String getChangeReason() {
		return this.changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE", nullable = false, length = 10)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE", length = 10)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "DESCRIPTION", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "NAME_CH", length = 100)
	public String getNameCh() {
		return this.nameCh;
	}

	public void setNameCh(String nameCh) {
		this.nameCh = nameCh;
	}

	@Column(name = "NAME_EN", length = 100)
	public String getNameEn() {
		return this.nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	@Column(name = "SEX", length = 2)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DATE", length = 10)
	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Column(name = "GRADUATED_SCHOOL", length = 100)
	public String getGraduatedSchool() {
		return this.graduatedSchool;
	}

	public void setGraduatedSchool(String graduatedSchool) {
		this.graduatedSchool = graduatedSchool;
	}

	@Column(name = "MAJOR", length = 100)
	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(name = "DEGREE", length = 100)
	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	@Column(name = "BASE_CODE", length = 100)
	public String getBaseCode() {
		return this.baseCode;
	}

	public void setBaseCode(String baseCode) {
		this.baseCode = baseCode;
	}

	@Column(name = "STAFF_NO", length = 100)
	public String getStaffNo() {
		return this.staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LENOVO_START_DATE", length = 10)
	public Date getLenovoStartDate() {
		return this.lenovoStartDate;
	}

	public void setLenovoStartDate(Date lenovoStartDate) {
		this.lenovoStartDate = lenovoStartDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "GAD_START_DATE", length = 10)
	public Date getGadStartDate() {
		return this.gadStartDate;
	}

	public void setGadStartDate(Date gadStartDate) {
		this.gadStartDate = gadStartDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DEMISSION_DATE", length = 10)
	public Date getDemissionDate() {
		return this.demissionDate;
	}

	public void setDemissionDate(Date demissionDate) {
		this.demissionDate = demissionDate;
	}

	@Column(name = "ADMINISTRATION_LEVEL", length = 1)
	public String getAdministrationLevel() {
		return this.administrationLevel;
	}

	public void setAdministrationLevel(String administrationLevel) {
		this.administrationLevel = administrationLevel;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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