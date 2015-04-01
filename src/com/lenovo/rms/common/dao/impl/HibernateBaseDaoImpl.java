package com.lenovo.rms.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;


import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lenovo.rms.common.dao.IHibernateBaseDao;
import com.lenovo.rms.common.util.ListPage;
import com.lenovo.rms.common.util.Page;
import com.lenovo.rms.common.util.ReflectUtils;

@Repository("hibernateBaseDao")
public class HibernateBaseDaoImpl<T, PK extends Serializable> implements IHibernateBaseDao<T,PK> {
    /**
     * log4j日志
     */
    protected final Logger logger = Logger.getLogger(getClass());
    @Autowired
    private SessionFactory sessionFactory;
    
    protected Class<T> entityClass;

    public Class<T> getEntityClass() {
        return entityClass;
    }

    /**
     * 在构造函数中利用反射机制获得参数T的具体类
     */
    public HibernateBaseDaoImpl() {
        entityClass = ReflectUtils.getClassGenricType(getClass());
    }
    
    private Session getSession(){
       return  sessionFactory.openSession();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(PK id) {
       return (T) this.getSession().get(entityClass, id);
       
    }

    @SuppressWarnings("unchecked")
    @Override
    public <X> X get(Class<X> clazz, PK id) {
        return (X) this.getSession().get(clazz, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T load(PK id) {
        return (T) this.getSession().load(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <X> X load(Class<X> clazz, PK id) {
        return (X) this.getSession().load(clazz, id);
    }

    @Override
    public void delete(T entity) {
        this.getSession().delete(entity);
        
    }

    @Override
    public void deleteAll(Collection<T> entities) {
        for(T entity : entities) {
            this.getSession().delete(entity);
        }
        
    }

    @Override
    public void deleteById(PK id) {
        T t = get(id);
        delete(t);
    }

    @Override
    public void deleteByIds(PK[] ids) {
        for(PK id:ids){
            deleteById(id);
        }   
    }

    @Override
    public void save(T entity) {
        this.getSession().save(entity);
        
    }

    @Override
    public void saveAll(Collection<T> entities) {
         for(T entity:entities){
             save(entity);
         }
    }

    @Override
    public void saveOrUpdate(T entity) {
        this.getSession().saveOrUpdate(entity);
    }

    @Override
    public void saveOrUpdateAll(Collection<T> entities) {
        for(T entity:entities){
            saveOrUpdate(entity);
        }
        
    }

    @Override
    public void persist(T entity) {
       this.getSession().persist(entity);
    }

    @Override
    public void update(T entity) {
       this.getSession().update(entity);
    }

    @Override
    public void updateAll(Collection<T> entities) {
        for(T entity:entities){
            update(entity);
        }
        
    }

    @Override
    public void flush() {
        this.getSession().flush();
        
    }

    @Override
    public void clear() {
        this.getSession().clear();
        
    }

    /* 
    * 简述
    * <p>详细说明第一行<br>    
    * 详细说明第二行 
    * @date 2015年4月1日 上午9:13:57   
    * @author Eric   
    * @param entity 
    * @see com.lenovo.rms.common.dao.impl.Iterfa#refresh(T) 
    */
    /* 
    * 简述
    * <p>详细说明第一行<br>    
    * 详细说明第二行 
    * @date 2015年4月1日 上午9:15:02   
    * @author Eric   
    * @param entity 
    * @see com.lenovo.rms.common.dao.impl.It#refresh(T) 
    */
    @Override
    public void refresh(T entity) {
        this.getSession().refresh(entity);
        
    }

//-------------------------------------------------------------------------------------------------
    /* 
    * 简述
    * <p>详细说明第一行<br>    
    * 详细说明第二行 
    * @date 2015年4月1日 上午9:13:57   
    * @author Eric   
    * @param hql
    * @param page
    * @return 
    * @see com.lenovo.rms.common.dao.impl.Iterfa#findHqlListPage(java.lang.String, com.lenovo.rms.common.util.Page) 
    */
    @Override
    public ListPage<T> findHqlListPage(final String hql, final Page page) {
        return findHqlListPage(hql, page, null);
    }
    
    /* 
    * 简述
    * <p>详细说明第一行<br>    
    * 详细说明第二行 
    * @date 2015年4月1日 上午9:13:57   
    * @author Eric   
    * @param hql
    * @param page
    * @param obj
    * @return 
    * @see com.lenovo.rms.common.dao.impl.Iterfa#findHqlListPage(java.lang.String, com.lenovo.rms.common.util.Page, java.lang.Object) 
    */
    @Override
    public ListPage<T> findHqlListPage(final String hql, final Page page,
            final Object obj) {
        List<T> list = findHql(hql, page, obj);
        page.setRowCount(list.size());
        return new ListPage<T>(list, page);
    }

    /* 
    * 简述
    * <p>详细说明第一行<br>    
    * 详细说明第二行 
    * @date 2015年4月1日 上午9:15:02   
    * @author Eric   
    * @param hql
    * @return 
    * @see com.lenovo.rms.common.dao.impl.It#findHql(java.lang.String) 
    */
    @Override
    public List<T> findHql(String hql) {
        return findHql(hql,null);
    }

    /* 
    * 简述
    * <p>详细说明第一行<br>    
    * 详细说明第二行 
    * @date 2015年4月1日 上午9:15:02   
    * @author Eric   
    * @param hql
    * @param obj
    * @return 
    * @see com.lenovo.rms.common.dao.impl.It#findHql(java.lang.String, java.lang.Object) 
    */
    @Override
    public List<T> findHql(final String hql, Object obj) {
        return findHql(hql, -1, -1, obj);
    }
    
    /* 
    * 简述
    * <p>详细说明第一行<br>    
    * 详细说明第二行 
    * @date 2015年4月1日 上午9:13:57   
    * @author Eric   
    * @param hql
    * @param pageIndex
    * @param pageSize
    * @return 
    * @see com.lenovo.rms.common.dao.impl.Iterfa#findHql(java.lang.String, int, int) 
    */
    @Override
    public List<T> findHql(final String hql, final int pageIndex,
            final int pageSize) {
        return findHql(hql, pageIndex, pageSize, null);
    }

    /* 
    * 简述
    * <p>详细说明第一行<br>    
    * 详细说明第二行 
    * @date 2015年4月1日 上午9:15:02   
    * @author Eric   
    * @param hql
    * @param page
    * @param obj
    * @return 
    * @see com.lenovo.rms.common.dao.impl.It#findHql(java.lang.String, com.lenovo.rms.common.util.Page, java.lang.Object) 
    */
    @Override
    public List<T> findHql(final String hql, final Page page,
            final Object obj){
        return this.findHql(hql, page.getCurrentRow(), page.getPageSize(),
                obj);
    }
 
    /* 
    * 简述
    * <p>详细说明第一行<br>    
    * 详细说明第二行 
    * @date 2015年4月1日 上午9:15:02   
    * @author Eric   
    * @param hql
    * @param pageIndex
    * @param pageSize
    * @param obj
    * @return 
    * @see com.lenovo.rms.common.dao.impl.It#findHql(java.lang.String, int, int, java.lang.Object) 
    */
    @Override
    public List<T> findHql(final String hql, final int pageIndex,
            final int pageSize, final Object obj) {
        return this.getQueryList(hql, pageIndex, pageSize, obj,2);
    }
    
    
    private List<T> getQueryList(final String queryString, final int pageIndex,
            final int pageSize, final Object obj,final int type){
        return getQueryList(queryString, pageIndex, pageSize, obj, entityClass, type);
    }
    @SuppressWarnings("unchecked")
    private <X>List<X> getQueryList(final String queryString, final int pageIndex,
            final int pageSize, final Object obj,final Class<X> clazz,final int type){
        
            Query query = getQuery(queryString, pageIndex, pageSize, obj,
                        type, this.getSession(),clazz);
                return query.list(); 
    }
    /* 
    * 简述
    * <p>详细说明第一行<br>    
    * 详细说明第二行 
    * @date 2015年4月1日 上午9:15:02   
    * @author Eric   
    * @param queryString
    * @param pageIndex
    * @param pageSize
    * @param param
    * @param type
    * @param session
    * @param entity
    * @return 
    * @see com.lenovo.rms.common.dao.impl.It#getQuery(java.lang.String, int, int, java.lang.Object, int, org.hibernate.Session, java.lang.Class) 
    */
    @Override
    public Query getQuery(final String queryString,
            final int pageIndex, final int pageSize, final Object param,
            final int type, Session session, Class<?> entity) {
        Query query = null; 
        if(type == 2){  //createQuery   HQL
            query= session.createQuery(queryString);
        }else if(type == 1){    //createSQLQuery    SQL
            if(queryString.toLowerCase().trim().startsWith("select count"))
                query = session.createSQLQuery(queryString);
            else
                query = session.createSQLQuery(queryString).addEntity(entity);
        }
        if (param != null){
            if(param instanceof Map<?,?>){
                query.setProperties((Map<?,?>)param);
            }else if(param.getClass().isArray()){
                int len = Array.getLength(param);
                for (int i = 0; i <len; i++) {
                    query.setParameter(i, Array.get(param, i));
                }
            }else{
                query.setProperties(param);
            }
        }
        setPageResult(query, pageIndex, pageSize);  //设置分页
        return query;
    }

    /* 
    * 简述
    * <p>详细说明第一行<br>    
    * 详细说明第二行 
    * @date 2015年4月1日 上午9:15:02   
    * @author Eric   
    * @param query
    * @param pageIndex
    * @param pageSize 
    * @see com.lenovo.rms.common.dao.impl.It#setPageResult(org.hibernate.Query, int, int) 
    */
    @Override
    public void setPageResult(Query query, int pageIndex, int pageSize){
        if (pageIndex >= 0) query.setFirstResult(pageIndex);
        if (pageSize > 0) query.setMaxResults(pageSize);
    }

   
    
    

}
