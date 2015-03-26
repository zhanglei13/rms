package com.lenovo.rms.common.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.lenovo.rms.common.dao.IHibernateBaseDao;
import com.lenovo.rms.common.util.PageView;

@Repository("hibernateBaseDao")
public class HibernateBaseDaoImpl {//implements IHibernateBaseDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	private static Logger logger = Logger.getLogger(HibernateTemplate.class);

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
}
