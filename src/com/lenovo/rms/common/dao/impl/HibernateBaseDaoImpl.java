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
     * log4j鏃ュ織
     */
    protected final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private SessionFactory sessionFactory;

    protected Class<T> entityClass;

    public Class<T> getEntityClass() {
        return entityClass;
    }

    /**
     * 鍦ㄦ瀯閫犲嚱鏁颁腑鍒╃敤鍙嶅皠鏈哄埗鑾峰緱鍙傛暟T鐨勫叿浣撶被
     */
    public HibernateBaseDaoImpl() {
        entityClass = ReflectUtils.getClassGenricType(getClass());
    }

    private Session getSession() {
        //return sessionFactory.getCurrentSession();
    	return sessionFactory.openSession();
    }
    
    //---------------------------浠ヤ笅鏄幏鍙栧璞＄殑鎺ュ彛瀹炵幇------------------------
    
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
    
    //---------------------------浠ヤ笅鏄垹闄ゅ璞＄殑鎺ュ彛瀹炵幇------------------------
    
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

    //---------------------------浠ヤ笅鏄柊澧炴垨鏇存柊瀵硅薄鐨勬帴鍙ｅ疄鐜�-----------------------
    
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

   //---------------------------浠ヤ笅鏄竻鐞嗘搷浣滄帴鍙ｅ疄鐜�-----------------------------
    
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

    //------------------------------浠ヤ笅鏄疕QL鎿嶄綔瀹炵幇------------------------------------------------
    
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
    public <X> List<X> findHql(String hql, Class<X> clazz){
    	return  getQueryList(hql, -1, -1, null, clazz, 2);
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

    
    //------------------------------浠ヤ笅鏄疩BC鎿嶄綔瀹炵幇------------------------------------------------
    
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


    //------------------------------浠ヤ笅鏄疩BE鎿嶄綔瀹炵幇------------------------------------------------
    

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

   
  //------------------------------浠ヤ笅鏄敤鏉ュ疄鐜版帴鍙ｇ殑杈呭姪鏂规硶------------------------------------------------
    
    /**   
    * 鏍规嵁鐩稿簲鏉′欢鐢熸垚query瀵硅薄
    * @date 2015骞�鏈�鏃�涓嬪崍2:52:16   
    * @author Eric   
    * @param queryString 鏌ヨ璇彞
    * @param pageIndex   鍒嗛〉鍏跺疄椤�
    * @param pageSize    鍒嗛〉澶у皬
    * @param param       鍙傛暟
    * @param type        绫诲瀷锛�浠ｈ〃SQL鏌ヨ锛�浠ｈ〃 HQL鏌ヨ
    * @param session     鐢ㄦ潵鐢熸垚query鐨剆ession
    * @param entity      绫诲瀷淇℃伅
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
        setPageResult(query, pageIndex, pageSize); // 璁剧疆鍒嗛〉
        return query;
    }
    
    /**   
    * 涓簈uery瀵硅薄璁剧疆鍒嗛〉淇℃伅
    * @date 2015骞�鏈�鏃�涓嬪崍2:51:12   
    * @author Eric   
    * @param query 寰呰缃垎椤典俊鎭殑query瀵硅薄
    * @param pageIndex 璧峰椤�
    * @param pageSize  椤靛ぇ灏�
    * void  
    */
    protected void setPageResult(Query query, long pageIndex, long pageSize) {
        if (pageIndex >= 0)
            query.setFirstResult((int)pageIndex);
        if (pageSize > 0)
            query.setMaxResults((int)pageSize);
    }

    
    
    /**   
    * 鏍规嵁鍙彉鏁伴噺鐨刢riterion鐢熸垚detachedCriteria
    * @date 2015骞�鏈�鏃�涓嬪崍2:48:25   
    * @author Eric   
    * @param criterions 鍙彉鏁伴噺鐨刢riterion
    * @return
    * DetachedCriteria  
    */
    protected DetachedCriteria createDetachedCriteria(final Criterion... criterions) {
        return createDetachedCriteria(entityClass, criterions);
    }

    /**   
    * 鏍规嵁鐩稿簲鐨勭被鍨嬩俊鎭拰鍙彉鏁伴噺鐨刢riterion鐢熸垚detachedCriteria
    * @date 2015骞�鏈�鏃�涓嬪崍2:46:40   
    * @author Eric   
    * @param clazz      绫诲瀷淇℃伅
    * @param criterions 鍙彉鏁伴噺鐨刢riterion
    * @return
    * DetachedCriteria  
    */
    @SuppressWarnings("rawtypes")
    protected DetachedCriteria createDetachedCriteria( final Class clazz, final Criterion... criterions) {
        return createDetachedCriteria(DetachedCriteria.forClass(clazz), criterions);
    }

    /**   
    * 鏍规嵁鍙彉鏁伴噺鐨刢riterion鐢熸垚绂荤嚎鏌ヨ鏉′欢detachedCriteria
    * @date 2015骞�鏈�鏃�涓嬪崍2:45:04   
    * @author Eric   
    * @param detachedCriteria 寰呯敓鎴愮殑绂荤嚎鏌ヨ鏉′欢detachedCriteria
    * @param criterions  鏁伴噺鍙彉鐨刢riterion
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
    * 鏍规嵁瀛楁鐢熸垚 like鐨刢rieterion
    * @date 2015骞�鏈�鏃�涓嬪崍2:43:45   
    * @author Eric   
    * @param values 鍙傛暟
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
    * 鐢熸垚 smart<=propertyName<=big鐨刢riterion
    * @date 2015骞�鏈�鏃�涓嬪崍2:42:00   
    * @author Eric   
    * @param propertyName 瀛楁
    * @param small       鏈�皬鍊�       
    * @param big         鏈�ぇ鍊�
    * @return
    * Criterion   
    */
    protected Criterion getBentweenCriterion(String propertyName, Object small, Object big) {
        if (small == null || big == null)
            return null;
        return Restrictions.between(propertyName, small, big);
    }

   
   
}
