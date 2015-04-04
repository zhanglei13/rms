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
@Table(name = "employee", catalog = "rms_dev")
public class Employee {

	// Fields

	private Integer empId;
	private String itCode;
	private String orgCode;
	private Long orgVersion;
	private String nameCh;
	private String nameEn;
	private String ladder;
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
	private String status;
	private String band;
	private String administrationLevel;
	private String position;
	private String linemanagerItcode;
	private String attrib1;
	private String attrib2;
	private Integer attrib3;

	// Constructors

	/** default constructor */
	public Employee() {
	}

	/** minimal constructor */
	public Employee(String itCode) {
		this.itCode = itCode;
	}

	/** full constructor */
	public Employee(Integer empId, String itCode, String orgCode,
			Long orgVersion, String nameCh, String nameEn, String ladder,
			String sex, Date birthDate, String graduatedSchool, String major,
			String degree, String baseCode, String staffNo,
			Date lenovoStartDate, Date gadStartDate, Date demissionDate,
			String status, String band, String administrationLevel,
			String position, String linemanagerItcode, String attrib1,
			String attrib2, Integer attrib3) {
		this.empId = empId;
		this.itCode = itCode;
		this.orgCode = orgCode;
		this.orgVersion = orgVersion;
		this.nameCh = nameCh;
		this.nameEn = nameEn;
		this.ladder = ladder;
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
		this.status = status;
		this.band = band;
		this.administrationLevel = administrationLevel;
		this.position = position;
		this.linemanagerItcode = linemanagerItcode;
		this.attrib1 = attrib1;
		this.attrib2 = attrib2;
		this.attrib3 = attrib3;
	}

	// Property accessors
	@Id
	@Column(name = "EMP_ID", unique = true, nullable = false)
	public Integer getEmpId() {
		return this.empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	@Column(name = "IT_CODE", length = 100)
	public String getItCode() {
		return this.itCode;
	}

	public void setItCode(String itCode) {
		this.itCode = itCode;
	}

	@Column(name = "ORG_CODE", length = 50)
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

	@Column(name = "LADDER", length = 20)
	public String getLadder() {
		return this.ladder;
	}

	public void setLadder(String ladder) {
		this.ladder = ladder;
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

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "BAND", length = 20)
	public String getBand() {
		return this.band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	@Column(name = "ADMINISTRATION_LEVEL", length = 1)
	public String getAdministrationLevel() {
		return this.administrationLevel;
	}

	public void setAdministrationLevel(String administrationLevel) {
		this.administrationLevel = administrationLevel;
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

	@Column(name = "ATTRIB1", length = 20)
	public String getAttrib1() {
		return this.attrib1;
	}

	public void setAttrib1(String attrib1) {
		this.attrib1 = attrib1;
	}

	@Column(name = "ATTRIB2", length = 20)
	public String getAttrib2() {
		return this.attrib2;
	}

	public void setAttrib2(String attrib2) {
		this.attrib2 = attrib2;
	}

	@Column(name = "ATTRIB3")
	public Integer getAttrib3() {
		return this.attrib3;
	}

	public void setAttrib3(Integer attrib3) {
		this.attrib3 = attrib3;
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