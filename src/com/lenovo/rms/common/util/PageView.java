package com.lenovo.rms.common.util;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PageView<T> {
	/** 分页数据 **/
	private List<T> records;
	/** 开始索引 **/
	private int startindex = 0;
	/** 结束索引 **/
	private int endindex = 0;
	/** 总页数 **/
	private int totalpage = 1;
	/** 每页显示记录数 **/
	private int maxresult = Constants.DEFALUT_PASESIZE;
	/** 当前页 **/
	private int currentpage = 1;
	/** 总记录数 **/
	private int totalrecord = 0;

	public PageView() {
	}

	public PageView(int maxresult, int currentpage) {
		this.maxresult = maxresult;
		this.currentpage = currentpage;
		// 设置开始数据下标
		setStartindex((currentpage - 1) * maxresult);
		// 设置结束下标
		setEndindex(currentpage * maxresult);
	}

	public PageView(int currentpage, int maxresult, int totalrecord,
			List<T> records) {
		this.currentpage = currentpage;
		this.maxresult = maxresult;
		setTotalrecord(totalrecord);
		this.records = records;
	}

	public int getTotalrecord() {
		return totalrecord;
	}

	// 设置总记录数
	public void setTotalrecord(int totalrecord) {
		this.totalrecord = totalrecord;
		// 计算并设置总页数
		setTotalpage(this.totalrecord % this.maxresult == 0 ? this.totalrecord
				/ this.maxresult : this.totalrecord / this.maxresult + 1);
	}

	public List<T> getRecords() {
		return records;
	}

	// 设置数据
	public void setRecords(List<T> records) {
		this.records = records;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public void setMaxresult(int maxresult) {
		this.maxresult = maxresult;
	}

	// 获取开始数据
	public int getStartindex() {
		return this.startindex;
	}

	public void setStartindex(int startindex) {
		this.startindex = startindex;
	}

	public int getEndindex() {
		return endindex;
	}

	public void setEndindex(int endindex) {
		this.endindex = endindex;
	}

	public int getMaxresult() {
		return maxresult;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
