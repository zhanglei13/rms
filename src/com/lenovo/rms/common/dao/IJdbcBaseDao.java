package com.lenovo.rms.common.dao;

import java.util.List;
import java.util.Map;

import com.lenovo.rms.common.util.PageView;

public interface IJdbcBaseDao {

	public abstract boolean update(String sql, Object... args);

	public abstract boolean add(String sql, Object... args);

	public abstract boolean del(String sql, Object... args);

	// 批量修改或添加
	public abstract int[] batchSaveAndUpdate(String sql[]);

	// 查询一条数据
	public abstract Map<String, Object> getOneForMap(String sql, Object... args);

	// 查询数据集
	public abstract List<Map<String, Object>> getSelectForList(String sql,
			Object... args);

	// 查询分页数据
	public abstract PageView<Map<String, Object>> getSelectForPageView(
			String sql, int currentpage, int maxresult, Object... args);
}
