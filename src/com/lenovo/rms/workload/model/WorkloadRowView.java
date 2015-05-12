package com.lenovo.rms.workload.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class WorkloadRowView {
	private String project;
	private String phase;
	private String date;
	private String monw;
	private String tusw;
	private String wedw;
	private String thuw;
	private String friw;
	private String satw;
	private String sunw;
	private String status;
	private int monh;
	private int tush;
	private int wedh;
	private int thuh;
	private int frih;
	private int sath;
	private int sunh;

	public WorkloadRowView() {
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMonw() {
		return monw;
	}

	public void setMonw(String monw) {
		this.monw = monw;
	}

	public String getTusw() {
		return tusw;
	}

	public void setTusw(String tusw) {
		this.tusw = tusw;
	}

	public String getWedw() {
		return wedw;
	}

	public void setWedw(String wedw) {
		this.wedw = wedw;
	}

	public String getThuw() {
		return thuw;
	}

	public void setThuw(String thuw) {
		this.thuw = thuw;
	}

	public String getFriw() {
		return friw;
	}

	public void setFriw(String friw) {
		this.friw = friw;
	}

	public String getSatw() {
		return satw;
	}

	public void setSatw(String satw) {
		this.satw = satw;
	}

	public String getSunw() {
		return sunw;
	}

	public void setSunw(String sunw) {
		this.sunw = sunw;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getMonh() {
		return monh;
	}

	public void setMonh(int monh) {
		this.monh = monh;
	}

	public int getTush() {
		return tush;
	}

	public void setTush(int tush) {
		this.tush = tush;
	}

	public int getWedh() {
		return wedh;
	}

	public void setWedh(int wedh) {
		this.wedh = wedh;
	}

	public int getThuh() {
		return thuh;
	}

	public void setThuh(int thuh) {
		this.thuh = thuh;
	}

	public int getFrih() {
		return frih;
	}

	public void setFrih(int frih) {
		this.frih = frih;
	}

	public int getSath() {
		return sath;
	}

	public void setSath(int sath) {
		this.sath = sath;
	}

	public int getSunh() {
		return sunh;
	}

	public void setSunh(int sunh) {
		this.sunh = sunh;
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
