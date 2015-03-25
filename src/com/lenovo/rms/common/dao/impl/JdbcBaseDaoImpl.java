package com.lenovo.rms.common.dao.impl;

import java.util.List;
import java.util.Map;

import com.lenovo.rms.common.dao.IJdbcBaseDao;
import com.lenovo.rms.common.util.PageView;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("jdbcBaseDao")
public class JdbcBaseDaoImpl implements IJdbcBaseDao {

	private static Logger logger = Logger.getLogger(JdbcBaseDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean update(String sql, Object... args) {
		try {
			logger.debug("update:" + sql);
			return getJdbcTemplate().update(sql, args) > 0;
		} catch (Exception e) {
			logger.debug((new StringBuilder("\u6570\u636E\u5E93\u5F02\u5E38--"))
					.append(e.getMessage()).toString());
		}
		return false;
	}

	@Override
	public boolean add(String sql, Object... args) {
		try {
			logger.debug("add:" + sql);
			return getJdbcTemplate().update(sql, args) > 0;
		} catch (Exception e) {
			logger.debug((new StringBuilder("\u6570\u636E\u5E93\u5F02\u5E38--"))
					.append(e.getMessage()).toString());

		}
		return false;
	}

	@Override
	public int[] batchSaveAndUpdate(String sql[]) {
		try {
			logger.debug("batchSaveAndUpdate:" + sql);
			return getJdbcTemplate().batchUpdate(sql);
		} catch (Exception e) {
			logger.debug((new StringBuilder("\u6570\u636E\u5E93\u5F02\u5E38--"))
					.append(e.getMessage()).toString());
		}
		return null;
	}

	@Override
	public boolean del(String sql, Object... args) {

		try {
			logger.debug("del:" + sql);
			return getJdbcTemplate().update(sql, args) > 0;
		} catch (Exception e) {
			logger.debug((new StringBuilder("\u6570\u636E\u5E93\u5F02\u5E38--"))
					.append(e.getMessage()).toString());
		}
		return false;
	}

	@Override
	public Map<String, Object> getOneForMap(String sql, Object... args) {
		try {
			logger.debug("getOneForMap:" + sql);
			List<Map<String, Object>> list = getJdbcTemplate().queryForList(
					sql, args);
			if (list != null && list.size() > 0)
				return list.get(0);
		} catch (Exception e) {
			logger.debug((new StringBuilder("\u6570\u636E\u5E93\u5F02\u5E38--"))
					.append(e.getMessage()).toString());
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getSelectForList(String sql,
			Object... args) {
		try {
			logger.debug("getSelectResult:" + sql);
			List<Map<String, Object>> list = getJdbcTemplate().queryForList(
					sql, args);
			return list;
		} catch (Exception e) {
			logger.debug((new StringBuilder("\u6570\u636E\u5E93\u5F02\u5E38--"))
					.append(e.getMessage()).toString());
		}
		return null;
	}

	@Override
	public PageView<Map<String, Object>> getSelectForPageView(String sql,
			int currentpage, int maxresult, Object... args) {
		try {
			String countQueryString = "select count(*) as count from (" + sql
					+ ") t";
			logger.debug("countQueryString:" + countQueryString);
			int totalCount = 0;
			totalCount = Integer.valueOf(getJdbcTemplate()
					.queryForMap(countQueryString, args).get("count")
					.toString());
			logger.debug(totalCount + "==========================");
			if (totalCount < 1) {
				return new PageView<Map<String, Object>>();
			} else {
				String pageQueryString = "select * from (" + sql + ") t LIMIT "
						+ ((currentpage - 1) * maxresult) + "," + maxresult
						+ "";
				logger.debug("pageQueryString:" + pageQueryString);
				List<Map<String, Object>> list = getJdbcTemplate()
						.queryForList(pageQueryString, args);
				// 返回pageView
				return new PageView<Map<String, Object>>(currentpage,
						maxresult, totalCount, list);
			}
		} catch (Exception e) {
			logger.debug((new StringBuilder("\u6570\u636E\u5E93\u5F02\u5E38--"))
					.append(e.getMessage()).toString());
		}
		return null;
	}

}
