package com.lenovo.rms.common.dao;

import java.io.Serializable;
import java.util.Collection;

public interface IHibernateBaseDao {

	public boolean update(Object entity);

	public boolean save(Object entity);

	public boolean delete(Object entity);
	
	public boolean saveOrUpdate(Object entity);
	
	public void deleteAll(Collection<?> entities);
	
	public <T> T get(Class<T> entityClass, Serializable id);
}
