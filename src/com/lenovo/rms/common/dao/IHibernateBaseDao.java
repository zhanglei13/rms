package com.lenovo.rms.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.lenovo.rms.common.util.Page;

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
	public void delete(final T entity);
	
	/*
	 * 批量删除指定对象集合
	 */
	public void deleteAll(final Collection<T> entities);
	/*
	 * 根据id删除对象
	 */
	public void deleteById(final PK id);
	/*
	 * 根据id组删除对象
	 */
	public void deleteByIds(final PK[] ids);
		
	/*
	 * 根据主键获取对象
	 */
	public T get(final PK id);
	
	/*
	 * 根据主键组获取对象
	 */
	public List<T> getAll(final PK[] ids);
	
	
	public T getHql(final String hql);
	
	
	public T getSql(final String sql);
	
	public List<T> findHql(final String hql);
	

	
	/** 
	* @author: Eric
	* @Title: findSql 
	* @Description: 这里用一句话描述这个方法的作用) 
	* @param: sql
	* @return: 设定文件 
	* @return List<T>    返回类型 
	*/
	public List<T> findSql(final String sql);
	
	/**
	 * 获取全部对象,带排序字段与升降序参
	 * @param entityClass	 要获得类型的class
	 * @param orderBy		排序字段名
	 * @param isAsc	是否是正序
	 * @return
	 */
	<X> List<X> findAll(Class<X> entityClass, String orderBy,
			boolean isAsc) ;
	
	<X> List<X> findAll(Class<X> entityClass, final Criterion[] criterions, 
			final Order[] orderBys, Page page) ;
	
	/** 
	 * @Author: Charles
	 * @Description: 获取全部对象,带查询、排序、不带分页
	 * @param entityClass
	 * @param criterions
	 * @param orderBys
	 * @return List<X>: 
	 */
	<X> List<X> findAll(Class<X> entityClass, final Criterion[] criterions, 
			final Order[] orderBys) ;
	
	 /**
	  * 根据条件获取数据,带分页
	  * @param page
	  * @param criterions 数量可变的Criterion(面向对象查询条件)
	  * @return
	  */
	 List<T> find(Page page,final Criterion... criterions) ;
	
}
