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

    /**
     * 根据条件获取数据
     * 
     * @param detachedCriteria
     *            hibernate 离线查询 DetachedCriteria
     */
    public <X> List<X> find(DetachedCriteria detachedCriteria) {
        return findByCriteria(detachedCriteria);
    }

    /**
     * 根据条件获取数据
     * 
     * @param firstResult
     *            第几条开始查询
     * @param maxResults
     *            最大返回数据行数
     * @param criterions
     *            数量可变的Criterion(面向对象查询条件)
     * @return
     */
    public List<T> find(int firstResult, int maxResults, final Criterion... criterions) {
        return findByCriteria(createDetachedCriteria(criterions), firstResult, maxResults);
    }

    /**
     * 根据条件获取数据
     * 
     * @param firstResult
     *            从第几条开始查询
     * @param maxResults
     *            最大返回数据行数
     * @param detachedCriteria
     *            hibernate 离线查询 DetachedCriteria
     * @return
     */
    public List<T> find(DetachedCriteria detachedCriteria, int firstResult, int maxResults) {
        return findByCriteria(detachedCriteria, firstResult, maxResults);
    }

    /**
     * 根据条件获取数据,带分页
     * 
     * @param page
     * @param criterions
     *            数量可变的Criterion(面向对象查询条件)
     * @return
     */
    public List<T> find(Page page, final Criterion... criterions) {
        return findByCriteria(createDetachedCriteria(criterions), page.getCurrentRow(),
                page.getPageSize());
    }

    /**
     * 根据条件获取数据,带分页
     * 
     * @param page
     * @param entity
     *            对象
     * @return
     */
    public List<T> find(final Page page, T entity) {
        return find(null, page, Example.create(entity));
    }

    /**
     * 根据条件获取数据,带分页
     * 
     * @param detachedCriteria
     *            hibernate 离线查询 DetachedCriteria
     * @param page
     * @param criterions
     *            数量可变的Criterion(面向对象查询条件)
     * @return
     */
    public List<T> find(DetachedCriteria detachedCriteria, Page page, final Criterion... criterions) {
        return findByCriteria(createDetachedCriteria(detachedCriteria, criterions),
                page.getCurrentRow(), page.getPageSize());
    }

    

    /**
     * ,根据条件获取数据,带分页,有总记录数
     * 
     * @param detachedCriteria
     *            hibernate 离线查询 DetachedCriteria
     * @param page
     * @param criterions
     *            数量可变的Criterion(面向对象查询条件)
     * @return
     */
    public ListPage<T> findListPage(DetachedCriteria detachedCriteria, final Page page, final Criterion... criterions) {
        detachedCriteria = detachedCriteria == null ? createDetachedCriteria(criterions) : this.createDetachedCriteria(
                detachedCriteria, criterions);
        List<T> ls = this.find(detachedCriteria, page.getCurrentRow(), page.getPageSize());
        page.setRowCount(this.getDetachedCriteriaRowCount(detachedCriteria));
        return new ListPage<T>(ls, page);
    }

    /**
     * ,根据条件获取数据,带分页,有总记录数
     * 
     * @param page
     * @param criterions
     *            数量可变的Criterion(面向对象查询条件)
     * @return
     */
    public ListPage<T> findListPage(final Page page, final Criterion... criterions) {
        return findListPage(null, page, criterions);
    }

    /**
     * ,根据条件获取数据,带分页,有总记录数
     * 
     * @param page
     * @param entity
     *            对象 里面的值为查询条件
     * @return
     */
    public ListPage<T> findListPage(final Page page, T entity) {
        return findListPage(null, page, Example.create(entity));
    }

    /**
     * 对象化查询
     * 
     * @param criterions
     *            数量可变的Criterion
     */
    public DetachedCriteria createDetachedCriteria(final Criterion... criterions) {
        return createDetachedCriteria(entityClass, criterions);
    }

    /**
     * 对象化查询
     * 
     * @param entityClass
     *            参数T的反射类型
     * @param criterions
     *            数量可变的Criterion
     */
    public DetachedCriteria createDetachedCriteria(final Class clazz, final Criterion... criterions) {
        return createDetachedCriteria(DetachedCriteria.forClass(clazz), criterions);
    }

    /**
     * 对象化查询
     * 
     * @param detachedCriteria
     *            离线查询 DetachedCriteria
     * @param criterions
     *            数量可变的Criterion
     */
    public DetachedCriteria createDetachedCriteria(DetachedCriteria detachedCriteria, final Criterion... criterions) {

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
     * 根据字段生成 like
     * 
     * @param clazz
     * @param values
     * @return
     */
    public Criterion[] getCriterionLike(Map<String, ?> values) {
        if (values == null || values.isEmpty())
            return null;
        List<Criterion> list = new ArrayList<Criterion>();
        for (Entry<String, ?> m : values.entrySet()) {
            list.add(Restrictions.like(m.getKey(), m.getValue()));
        }
        return list.toArray(new Criterion[0]);
    }

    /**
     * 生成 smart<=propertyName<=big
     * 
     * @return
     */
    public Criterion getBentweenCriterion(String propertyName, Object smart, Object big) {
        if (smart == null || big == null)
            return null;
        return Restrictions.between(propertyName, smart, big);
    }

   

    /**
     * QBE（Query By Example）DetachedCriteria 查询方式 查询数据的总行
     * 
     * @param entity
     * @return
     */
    public int getDetachedCriteriaRowCount(final T entity) {
        return getDetachedCriteriaRowCount(entityClass, Example.create(entity));
    }

    /**
     * QBE（Query By Example）DetachedCriteria 查询方式 查询数据的总行
     * 
     * @param criterions
     * @param clazz
     * @return
     */
    public int getDetachedCriteriaRowCount(Class<?> clazz, final Criterion... criterions) {
        return getDetachedCriteriaRowCount(DetachedCriteria.forClass(clazz), criterions);
    }

    /**
     * QBE（Query By Example）DetachedCriteria 查询方式 查询数据的总行
     * 
     * @param entity
     * @return
     */
    public int getDetachedCriteriaRowCount(final DetachedCriteria detachedCriteria, final Criterion... criterions) {
        this.createDetachedCriteria(detachedCriteria, criterions);
        Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
        int totalCount = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
        return totalCount;

    }

    /**
     * @Author: Charles
     * @Description: 根据HQL查询数据总数
     * @param hql
     * @return
     */
    public int getHqlRowCount(String hql) {
        return getHqlRowCount(hql, null);
    }

    /**
     * @Author: Charles
     * @Description: 根据HQL查询数据总数
     * @param hql
     * @return long:
     */
    public int getHqlRowCount(String hql, Object obj) {
        if (!hql.trim().toLowerCase().startsWith("from")) {
            hql = hql.substring(hql.toLowerCase().indexOf("from "));
        }
        hql = "select count(*) " + hql;
        List<?> list = this.findHql(hql, obj);
        int row = ((Long) list.get(0)).intValue();
        return row;
    }

}
