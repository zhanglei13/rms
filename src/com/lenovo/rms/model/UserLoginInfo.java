package com.lenovo.rms.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "user_login_info", catalog = "rms_dev")
public class UserLoginInfo {

	// Fields

	private Integer id;
	private String loginName;
	private String loginPassword;
	private String loginType;
	private String lastLoginIp;
	private Timestamp lastLoginTime;

	// Constructors

	/** default constructor */
	public UserLoginInfo() {
	}

	/** minimal constructor */
	public UserLoginInfo(Integer id, Timestamp lastLoginTime) {
		this.id = id;
		this.lastLoginTime = lastLoginTime;
	}

	/** full constructor */
	public UserLoginInfo(Integer id, String loginName, String loginPassword,
			String loginType, String lastLoginIp, Timestamp lastLoginTime) {
		this.id = id;
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.loginType = loginType;
		this.lastLoginIp = lastLoginIp;
		this.lastLoginTime = lastLoginTime;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "LOGIN_NAME", length = 100)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "LOGIN_PASSWORD", length = 100)
	public String getLoginPassword() {
		return this.loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	@Column(name = "LOGIN_TYPE", length = 1)
	public String getLoginType() {
		return this.loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	@Column(name = "LAST_LOGIN_IP", length = 30)
	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	@Column(name = "LAST_LOGIN_TIME", nullable = false, length = 19)
	public Timestamp getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
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