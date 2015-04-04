package com.lenovo.rms.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lenovo.rms.common.dao.IHibernateBaseDao;
import com.lenovo.rms.common.util.ListPage;
import com.lenovo.rms.common.util.Page;
import com.lenovo.rms.common.util.ReflectUtils;

@Repository("hibernateBaseDao")
public class HibernateBaseDaoImpl<T, PK extends Serializable> implements IHibernateBaseDao<T, PK> {
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

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    //---------------------------以下是获取对象的接口实现------------------------
    
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
    
    //---------------------------以下是删除对象的接口实现------------------------
    
    @Override
    public void delete(T entity) {
        this.getSession().delete(entity);

    }

    @Override
    public void deleteAll(Collection<T> entities) {
        for (T entity : entities) {
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
        for (PK id : ids) {
            deleteById(id);
        }
    }

    //---------------------------以下是新增或更新对象的接口实现------------------------
    
    @Override
    public void save(T entity) {
        this.getSession().save(entity);

    }

    @Override
    public void saveAll(Collection<T> entities) {
        for (T entity : entities) {
            save(entity);
        }
    }

    @Override
    public void saveOrUpdate(T entity) {
        this.getSession().saveOrUpdate(entity);
    }
    
    @Override
    public <X> void saveOrUpdate(Class<X> clazz, X entity) {
        this.getSession().saveOrUpdate(entity);
    }
    @Override
    public void saveOrUpdateAll(Collection<T> entities) {
        for (T entity : entities) {
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
        for (T entity : entities) {
            update(entity);
        }

    }

   //---------------------------以下是清理操作接口实现------------------------------
    
    @Override
    public void flush() {
        this.getSession().flush();

    }

    @Override
    public void clear() {
        this.getSession().clear();

    }

    @Override
    public void refresh(T entity) {
        this.getSession().refresh(entity);

    }

    //------------------------------以下是HQL操作实现------------------------------------------------
    
    @Override
    public ListPage<T> findHqlListPage(final String hql, final Page page) {
        return findHqlListPage(hql, page, null);
    }

    @Override
    public ListPage<T> findHqlListPage(final String hql, final Page page, final Object obj) {
        List<T> list = findHql(hql, page, obj);
        page.setRowCount(list.size());
        return new ListPage<T>(list, page);
    }

    @Override
    public List<T> findHql(String hql) {
        return findHql(hql, null);
    }

    @Override
    public List<T> findHql(final String hql, Object obj) {
        return findHql(hql, -1, -1, obj);
    }

    @Override
    public List<T> findHql(final String hql, final long pageIndex, final long pageSize) {
        return findHql(hql, pageIndex, pageSize, null);
    }

    @Override
    public List<T> findHql(final String hql, final Page page, final Object obj) {
        return this.findHql(hql, page.getCurrentRow(), page.getPageSize(), obj);
    }

    @Override
    public List<T> findHql(final String hql, final long pageIndex, final long pageSize, final Object obj) {
        return this.getQueryList(hql, pageIndex, pageSize, obj, 2);
    }

    protected List<T> getQueryList(final String queryString, final long pageIndex, final long pageSize, final Object obj,
            final long type) {
        return getQueryList(queryString, pageIndex, pageSize, obj, entityClass, type);
    }
   
    @Override
    public long getHqlRowCount(String hql) {
        return getHqlRowCount(hql, null);
    }

    @Override
    public long getHqlRowCount(String hql, Object obj) {
        if (!hql.trim().toLowerCase().startsWith("from")) {
            hql = hql.substring(hql.toLowerCase().indexOf("from "));
        }
        hql = "select count(*) " + hql;
        List<?> list = this.findHql(hql, obj);
        long row = ((Long) list.get(0)).intValue();
        return row;
    }

    @SuppressWarnings("unchecked")
    protected <X> List<X> getQueryList(final String queryString, final long pageIndex, final long pageSize,
            final Object obj, final Class<X> clazz, final long type) {

        Query query = getQuery(queryString, pageIndex, pageSize, obj, type, this.getSession(), clazz);
        return query.list();
    }

    
    //------------------------------以下是QBC操作实现------------------------------------------------
    
    @SuppressWarnings("rawtypes")
    @Override
    public List findByCriteria(final DetachedCriteria criteria, final long firstResult, final long maxResults) {

        Criteria executableCriteria = criteria.getExecutableCriteria(getSession());
        if (firstResult >= 0) {
            executableCriteria.setFirstResult((int)firstResult);
        }
        if (maxResults > 0) {
            executableCriteria.setMaxResults((int)maxResults);
        }
        return executableCriteria.list();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List findByCriteria(DetachedCriteria criteria) {
        return findByCriteria(criteria, -1, -1);
    }

    
    @SuppressWarnings("unchecked")
    @Override
    public <X> List<X> findAll(Class<X> entityClass, final Criterion[] criterions, final Order[] orderBys,
            long firstResult, long maxResults) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
        if (criterions != null) {
            for (Criterion c : criterions) {
                detachedCriteria.add(c);
            }
        }
        if (orderBys != null) {
            for (Order o : orderBys) {
                detachedCriteria.addOrder(o);
            }
        }
        return findByCriteria(detachedCriteria, firstResult, maxResults);
    }


    @SuppressWarnings("unchecked")
    @Override
    public <X> List<X> findAll(Class<X> entityClass, String orderBy, boolean isAsc) {
        if (isAsc)
            return findByCriteria(DetachedCriteria.forClass(entityClass).addOrder(Order.asc(orderBy)));
        else
            return findByCriteria(DetachedCriteria.forClass(entityClass).addOrder(Order.desc(orderBy)));
    }

    @Override
    public <X> List<X> findAll(Class<X> entityClass, final Criterion[] criterions, final Order[] orderBys, Page page) {
        return this.findAll(entityClass, criterions, orderBys, page.getCurrentRow(), page.getPageSize());
    }


    @Override
    public <X> List<X> findAll(Class<X> entityClass, final Criterion[] criterions, final Order[] orderBys) {
        return this.findAll(entityClass, criterions, orderBys, -1, -1);
    }

 
    @Override
    public List<T> findAll(String orderBy, boolean isAsc) {
        return findAll(entityClass, orderBy, isAsc);
    }

  
    @Override
    @SuppressWarnings("unchecked")
    public <X> List<X> findAll(Class<X> entityClass) {
        return findByCriteria(DetachedCriteria.forClass(entityClass));
    }

    @Override
    public List<T> findAll() {
        return findAll(entityClass);
    }

    @Override
    public T findUnique(final Criterion... criterions) {
        return findUnique(entityClass, criterions);
    }

    @Override
    public <X> X findUnique(Class<X> clazz, final Criterion... criterions) {
        Criteria executableCriteria = getSession().createCriteria(clazz);
        for (Criterion c : criterions) {
            executableCriteria.add(c);
        }
        @SuppressWarnings("unchecked")
        X result = (X) executableCriteria.uniqueResult();
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <X> List<X> find(DetachedCriteria detachedCriteria) {
        return findByCriteria(detachedCriteria);
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<T> find(long firstResult, long maxResults, final Criterion... criterions) {
        return findByCriteria(createDetachedCriteria(criterions), firstResult, maxResults);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> find(DetachedCriteria detachedCriteria, long firstResult, long maxResults) {
        return findByCriteria(detachedCriteria, firstResult, maxResults);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> find(Page page, final Criterion... criterions) {
        return findByCriteria(createDetachedCriteria(criterions), page.getCurrentRow(),
                page.getPageSize());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> find(DetachedCriteria detachedCriteria, Page page, final Criterion... criterions) {
        return findByCriteria(createDetachedCriteria(detachedCriteria, criterions),
                page.getCurrentRow(), page.getPageSize());
    }

    @Override
    public ListPage<T> findListPage(DetachedCriteria detachedCriteria, final Page page, final Criterion... criterions) {
        detachedCriteria = detachedCriteria == null ? createDetachedCriteria(criterions) : this.createDetachedCriteria(
                detachedCriteria, criterions);
        List<T> ls = this.find(detachedCriteria, page.getCurrentRow(), page.getPageSize());
        page.setRowCount(this.getDetachedCriteriaRowCount(detachedCriteria));
        return new ListPage<T>(ls, page);
    }

    @Override
    public ListPage<T> findListPage(final Page page, final Criterion... criterions) {
        return findListPage(null, page, criterions);
    }

   
    
    @Override
    public long getDetachedCriteriaRowCount(Class<?> clazz, final Criterion... criterions) {
        return getDetachedCriteriaRowCount(DetachedCriteria.forClass(clazz), criterions);
    }

    @Override
    public long getDetachedCriteriaRowCount(final DetachedCriteria detachedCriteria, final Criterion... criterions) {
        this.createDetachedCriteria(detachedCriteria, criterions);
        Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
        long totalCount = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
        return totalCount;

    }


    //------------------------------以下是QBE操作实现------------------------------------------------
    

    @Override
    public List<T> find(final Page page, T entity) {
        return find(null, page, Example.create(entity));
    }
    @Override
    public ListPage<T> findListPage(final Page page, T entity) {
        return findListPage(null, page, Example.create(entity));
    }

    @Override
    public long getDetachedCriteriaRowCount(final T entity) {
        return getDetachedCriteriaRowCount(entityClass, Example.create(entity));
    }

   
  //------------------------------以下是用来实现接口的辅助方法------------------------------------------------
    
    /**   
    * 根据相应条件生成query对象
    * @date 2015年4月4日 下午2:52:16   
    * @author Eric   
    * @param queryString 查询语句
    * @param pageIndex   分页其实页
    * @param pageSize    分页大小
    * @param param       参数
    * @param type        类型：1代表SQL查询，2代表 HQL查询
    * @param session     用来生成query的session
    * @param entity      类型信息
    * @return
    * Query  
    */
    protected Query getQuery(final String queryString, final long pageIndex, final long pageSize, final Object param,
            final long type, Session session, Class<?> entity) {
        Query query = null;
        if (type == 2) { // createQuery HQL
            query = session.createQuery(queryString);
        } else if (type == 1) { // createSQLQuery SQL
            if (queryString.toLowerCase().trim().startsWith("select count"))
                query = session.createSQLQuery(queryString);
            else
                query = session.createSQLQuery(queryString).addEntity(entity);
        }
        if (param != null) {
            if (param instanceof Map<?, ?>) {
                query.setProperties((Map<?, ?>) param);
            } else if (param.getClass().isArray()) {
                int len = Array.getLength(param);
                for (int i = 0; i < len; i++) {
                    query.setParameter(i, Array.get(param, i));
                }
            } else {
                query.setProperties(param);
            }
        }
        setPageResult(query, pageIndex, pageSize); // 设置分页
        return query;
    }
    
    /**   
    * 为query对象设置分页信息
    * @date 2015年4月4日 下午2:51:12   
    * @author Eric   
    * @param query 待设置分页信息的query对象
    * @param pageIndex 起始页
    * @param pageSize  页大小
    * void  
    */
    protected void setPageResult(Query query, long pageIndex, long pageSize) {
        if (pageIndex >= 0)
            query.setFirstResult((int)pageIndex);
        if (pageSize > 0)
            query.setMaxResults((int)pageSize);
    }

    
    
    /**   
    * 根据可变数量的criterion生成detachedCriteria
    * @date 2015年4月4日 下午2:48:25   
    * @author Eric   
    * @param criterions 可变数量的criterion
    * @return
    * DetachedCriteria  
    */
    protected DetachedCriteria createDetachedCriteria(final Criterion... criterions) {
        return createDetachedCriteria(entityClass, criterions);
    }

    /**   
    * 根据相应的类型信息和可变数量的criterion生成detachedCriteria
    * @date 2015年4月4日 下午2:46:40   
    * @author Eric   
    * @param clazz      类型信息
    * @param criterions 可变数量的criterion
    * @return
    * DetachedCriteria  
    */
    @SuppressWarnings("rawtypes")
    protected DetachedCriteria createDetachedCriteria( final Class clazz, final Criterion... criterions) {
        return createDetachedCriteria(DetachedCriteria.forClass(clazz), criterions);
    }

    /**   
    * 根据可变数量的criterion生成离线查询条件detachedCriteria
    * @date 2015年4月4日 下午2:45:04   
    * @author Eric   
    * @param detachedCriteria 待生成的离线查询条件detachedCriteria
    * @param criterions  数量可变的criterion
    * @return
    * DetachedCriteria  
    */
    protected DetachedCriteria createDetachedCriteria(DetachedCriteria detachedCriteria, final Criterion... criterions) {

        if (detachedCriteria == null)
            detachedCriteria = DetachedCriteria.forClass(entityClass);
        if (criterions == null)
            return detachedCriteria;
        for (Criterion d : criterions) {
            detachedCriteria.add(d);
        }
        return detachedCriteria;
    }

    /**   
    * 根据字段生成 like的crieterion
    * @date 2015年4月4日 下午2:43:45   
    * @author Eric   
    * @param values 参数
    * @return
    * Criterion[]  
    */
    protected Criterion[] getCriterionLike(Map<String, ?> values) {
        if (values == null || values.isEmpty())
            return null;
        List<Criterion> list = new ArrayList<Criterion>();
        for (Entry<String, ?> m : values.entrySet()) {
            list.add(Restrictions.like(m.getKey(), m.getValue()));
        }
        return list.toArray(new Criterion[0]);
    }

    /**   
    * 生成 smart<=propertyName<=big的criterion
    * @date 2015年4月4日 下午2:42:00   
    * @author Eric   
    * @param propertyName 字段
    * @param small       最小值        
    * @param big         最大值
    * @return
    * Criterion   
    */
    protected Criterion getBentweenCriterion(String propertyName, Object small, Object big) {
        if (small == null || big == null)
            return null;
        return Restrictions.between(propertyName, small, big);
    }

   
   
}
