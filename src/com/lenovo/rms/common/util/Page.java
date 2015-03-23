package com.lenovo.rms.common.util;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Page {
	private Integer pageNo = Constants.DEFALUT_PAGE;
	private Integer pageSize = Constants.DEFALUT_PASESIZE;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
