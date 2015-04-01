package com.lenovo.rms.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
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
        return sessionFactory.openSession();
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

    // -------------------------------------------------------------------------------------------------

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
    public List<T> findHql(final String hql, final int pageIndex, final int pageSize) {
        return findHql(hql, pageIndex, pageSize, null);
    }

    @Override
    public List<T> findHql(final String hql, final Page page, final Object obj) {
        return this.findHql(hql, page.getCurrentRow(), page.getPageSize(), obj);
    }

    @Override
    public List<T> findHql(final String hql, final int pageIndex, final int pageSize, final Object obj) {
        return this.getQueryList(hql, pageIndex, pageSize, obj, 2);
    }

    private List<T> getQueryList(final String queryString, final int pageIndex, final int pageSize, final Object obj,
            final int type) {
        return getQueryList(queryString, pageIndex, pageSize, obj, entityClass, type);
    }

    @SuppressWarnings("unchecked")
    private <X> List<X> getQueryList(final String queryString, final int pageIndex, final int pageSize,
            final Object obj, final Class<X> clazz, final int type) {

        Query query = getQuery(queryString, pageIndex, pageSize, obj, type, this.getSession(), clazz);
        return query.list();
    }

    public Query getQuery(final String queryString, final int pageIndex, final int pageSize, final Object param,
            final int type, Session session, Class<?> entity) {
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

    public void setPageResult(Query query, int pageIndex, int pageSize) {
        if (pageIndex >= 0)
            query.setFirstResult(pageIndex);
        if (pageSize > 0)
            query.setMaxResults(pageSize);
    }

    @SuppressWarnings("rawtypes")
    public List findByCriteria(final DetachedCriteria criteria, final int firstResult, final int maxResults) {

        Criteria executableCriteria = criteria.getExecutableCriteria(getSession());
        if (firstResult >= 0) {
            executableCriteria.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            executableCriteria.setMaxResults(maxResults);
        }
        return executableCriteria.list();
    }

    @SuppressWarnings("rawtypes")
    public List findByCriteria(DetachedCriteria criteria) {
        return findByCriteria(criteria, -1, -1);
    }

    /**
     * QBC 查询方式：
     * 
     * @Author: Charles
     * @Description: 获取全部对象,带查询、排序、分页
     * @param entityClass
     * @param criterions
     * @param orderBys
     * @param firstResult
     * @param maxResults
     * @return List<X>:
     */
    @SuppressWarnings("unchecked")
    public <X> List<X> findAll(Class<X> entityClass, final Criterion[] criterions, final Order[] orderBys,
            int firstResult, int maxResults) {
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

    /**
     * 获取全部对象,带排序字段与升降序参
     * 
     * @Author: fjz
     * @param entityClass
     *            要获得类型的class
     * @param orderBy
     *            排序字段名
     * @param isAsc
     *            是否是正序
     * @return
     */
    @SuppressWarnings("unchecked")
    public <X> List<X> findAll(Class<X> entityClass, String orderBy, boolean isAsc) {
        if (isAsc)
            return findByCriteria(DetachedCriteria.forClass(entityClass).addOrder(Order.asc(orderBy)));
        else
            return findByCriteria(DetachedCriteria.forClass(entityClass).addOrder(Order.desc(orderBy)));
    }

    /**
     * @Author: Charles
     * @Description: 获取全部对象,带查询、排序、分页
     * @param entityClass
     * @param criterions
     * @param orderBys
     * @param page
     * @return List<X>:
     */
    public <X> List<X> findAll(Class<X> entityClass, final Criterion[] criterions, final Order[] orderBys, Page page) {
        return this.findAll(entityClass, criterions, orderBys, page.getCurrentRow(), page.getPageSize());
    }

    /**
     * @Author: Charles
     * @Description: 获取全部对象,带查询、排序、不带分页
     * @param entityClass
     * @param criterions
     * @param orderBys
     * @return List<X>:
     */
    public <X> List<X> findAll(Class<X> entityClass, final Criterion[] criterions, final Order[] orderBys) {
        return this.findAll(entityClass, criterions, orderBys, -1, -1);
    }

    /**
     * 获取全部对象,带排序字段与升降序参
     * 
     * @Author: fjz
     * @param orderBy
     *            排序字段名
     * @param isAsc
     *            是否是正序
     * @return
     */
    public List<T> findAll(String orderBy, boolean isAsc) {
        return findAll(entityClass, orderBy, isAsc);
    }

    /**
     * 获取全部对象
     * 
     * @Author: fjz
     * @param entityClass
     *            要获得类型的class
     * @return
     */
    @SuppressWarnings("unchecked")
    public <X> List<X> findAll(Class<X> entityClass) {
        return findByCriteria(DetachedCriteria.forClass(entityClass));
    }

    /**
     * 获取全部对象
     */
    public List<T> findAll() {
        return findAll(entityClass);
    }

    public T findUnique(final Criterion... criterions) {
        return findUnique(entityClass, criterions);
    }

    public <X> X findUnique(Class<X> clazz, final Criterion... criterions) {
        Criteria executableCriteria = getSession().createCriteria(clazz);
        for (Criterion c : criterions) {
            executableCriteria.add(c);
        }
        @SuppressWarnings("unchecked")
        X result = (X) executableCriteria.uniqueResult();
        return result;
    }

}
