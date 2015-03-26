package com.lenovo.rms.common.dao;

import java.io.Serializable;
import java.util.Collection;

public interface IHibernateBaseDao<T, PK extends Serializable> {
	/*
	 * 新增一个实体记录
	 */
	public void save(T entity);
	/*
	 * 批量添加实体记录
	 */
	public void saveAll(Collection<T> entities);
	/*
	 * 更新一个实体记录
	 */
	public void update(T entity);
	/*
	 * 批量更新实体记录
	 */
	public void updateAll(Collection<T> entities);
	/*
	 * 添加或更新实体记录
	 */
	public void saveOrUpdate(T entity);
	/*
	 * 批量添加或更新实体记录
	 */
	public void saveOrUpdateAll(Collection<T> entities);
	/*
	 * 删除指定对象
	 */
	public void delete(T entity);
	
	/*
	 * 批量删除指定对象集合
	 */
	public void deleteAll(Collection<T> entities);
	/*
	 * 根据id删除对象
	 */
	public void deleteById(PK id);
	/*
	 * 根据id组删除对象
	 */
	public void deleteByIds(PK[] ids);
		
	/*
	 * 根据主键获取对象
	 */
	public T get(PK id);
}
