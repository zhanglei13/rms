package com.lenovo.rms.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.lenovo.rms.common.util.ListPage;
import com.lenovo.rms.common.util.Page;
import com.lenovo.rms.common.util.PageView;

/**   
* Hibernate基本dao操作接口
* @date 2015年3月27日 下午8:46:24   
* @author Eric   
* @version V1.0   
*/
public interface IHibernateBaseDao<T, PK extends Serializable> { 
    
    //---------------------------以下是获取对象的接口------------------------
    /**   
    * 根据ID获取对象
    * @date 2015年3月28日 下午3:15:03   
    * @author Eric   
    * @param id 对象Id
    * @return T 获取的对象
    */
    T get(final PK id);
    
    /**   
    * 根据对象类型和ID获取对象
    * @date 2015年3月28日 下午3:17:52   
    * @author Eric   
    * @param clazz 要获取的对象类型
    * @param id    要获取的对象Id
    * @return X    获取的对象
    */
    <X> X get(Class<X> clazz, final PK id);
    
    /**   
    * 根据ID获取对象   
    * @date 2015年3月30日 下午6:52:42   
    * @author Eric   
    * @param id 要获取对象的ID
    * @return T 获取的对象  
    */
    T load(final PK id);
    
    /**   
     * 根据对象类型和ID获取对象
     * @date 2015年3月28日 下午3:17:52   
     * @author Eric   
     * @param clazz 要获取的对象类型
     * @param id    要获取的对象Id
     * @return X    获取的对象
     */
    <X> X load(Class<X> clazz, final PK id);
   
    //---------------------------以下是删除对象的接口------------------------
    /**   
    * 删除一个实体对象
    * @date 2015年3月30日 下午6:54:26   
    * @author Eric   
    * @param   entity 要删除的实体对象
    * @return  void  
    */
    void delete(final T entity) ;
  
    /**   
    * 批量删除集合中的对象
    * @date 2015年3月30日 下午6:55:55   
    * @author Eric   
    * @param entities 待删除的对象集合
    * @return void  
    */
    void deleteAll(final Collection<T> entities);

    /**   
    * 根据ID删除对象 
    * @date 2015年3月30日 下午6:57:10   
    * @author Eric   
    * @param  id 待删除对象的ID
    * @return void  
    */
    void deleteById(final PK id);

    /**   
    * 根据ID批量删除对象
    * @date 2015年3月30日 下午6:58:17   
    * @author Eric   
    * @param ids 待删除对象的ID
    * @return void  
    */
    void deleteByIds(final PK [] ids) ;
    
    //---------------------------以下是新增或更新对象的接口------------------------
    /**   
    * 新增一个实体记录
    * @date 2015年3月30日 下午7:03:51   
    * @author Eric   
    * @param entity 待添加的实体
    * @return void  
    */
    void save(T entity) ;
  
    /**   
    * 批量添加实体记录
    * @date 2015年3月30日 下午7:04:40   
    * @author Eric   
    * @param entities 待添加的记录集合
    * @return void  
    */
    void saveAll(Collection<T> entities) ;
    
    /**   
    * 新增或更新一个实体记录
    * @date 2015年3月30日 下午7:05:25   
    * @author Eric   
    * @param entity 待新增或更新的实体
    * @return void  
    */
    void saveOrUpdate(final T entity) ;
    
    /**   
    * 批量新增或更新实体 
    * @date 2015年3月30日 下午7:05:59   
    * @author Eric   
    * @param entities 待新增或更新的实体
    * @return void  
    */
    void saveOrUpdateAll(final Collection<T> entities) ;
    
    /**   
    * 持久化一个实体记录
    * @date 2015年3月30日 下午7:07:05   
    * @author Eric   
    * @param entity 待持久化的实体
    * @return void  
    */
    void persist(T entity) ;

    /**   
    * 更新一个实体记录
    * @date 2015年3月30日 下午7:07:43   
    * @author Eric   
    * @param entity 待更新的实体
    * @return void  
    */
    void update(T entity) ;

    /**   
    * 批量更新实体记录
    * @date 2015年3月30日 下午7:09:28   
    * @author Eric   
    * @param entities 待更新的实体记录
    * @return void  
    */
    void updateAll(Collection<T> entities) ;
    
    //---------------------------以下是清理操作接口------------------------------
    /**   
    * 强制进行从内存到数据库的同步
    * @date 2015年3月30日 下午7:09:59   
    * @author Eric   
    * @return void  
    */
    void flush() ;
    
    /**   
    * 清除Session缓存，执行SQL
    * @date 2015年3月30日 下午7:11:37   
    * @author  Eric   
    * @reuturn void  
    */
    void clear() ;
    
    /**   
    * 更新实体记录 
    * @date 2015年4月1日 上午9:19:31   
    * @author Eric   
    * @param entity 待更新的实体
    * @return void  
    */
    void refresh(T entity);

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
    ListPage<T> findHqlListPage(String hql, Page page);

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
    ListPage<T> findHqlListPage(String hql, Page page, Object obj);

    /* 
     * 简述
     * <p>详细说明第一行<br>    
     * 详细说明第二行 
     * @date 2015年4月1日 上午9:13:57   
     * @author Eric   
     * @param hql
     * @return 
     * @see com.lenovo.rms.common.dao.impl.Iterfa#findHql(java.lang.String) 
     */
    List<T> findHql(String hql);

    /* 
     * 简述
     * <p>详细说明第一行<br>    
     * 详细说明第二行 
     * @date 2015年4月1日 上午9:13:57   
     * @author Eric   
     * @param hql
     * @param obj
     * @return 
     * @see com.lenovo.rms.common.dao.impl.Iterfa#findHql(java.lang.String, java.lang.Object) 
     */
    List<T> findHql(String hql, Object obj);
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
    List<T> findHql(String hql, int pageIndex, int pageSize);

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
     * @see com.lenovo.rms.common.dao.impl.Iterfa#findHql(java.lang.String, com.lenovo.rms.common.util.Page, java.lang.Object) 
     */
    List<T> findHql(String hql, Page page, Object obj);

    /* 
     * 简述
     * <p>详细说明第一行<br>    
     * 详细说明第二行 
     * @date 2015年4月1日 上午9:13:57   
     * @author Eric   
     * @param hql
     * @param pageIndex
     * @param pageSize
     * @param obj
     * @return 
     * @see com.lenovo.rms.common.dao.impl.Iterfa#findHql(java.lang.String, int, int, java.lang.Object) 
     */
    List<T> findHql(String hql, int pageIndex, int pageSize, Object obj);

    /* 
     * 简述
     * <p>详细说明第一行<br>    
     * 详细说明第二行 
     * @date 2015年4月1日 上午9:13:57   
     * @author Eric   
     * @param queryString
     * @param pageIndex
     * @param pageSize
     * @param param
     * @param type
     * @param session
     * @param entity
     * @return 
     * @see com.lenovo.rms.common.dao.impl.Iterfa#getQuery(java.lang.String, int, int, java.lang.Object, int, org.hibernate.Session, java.lang.Class) 
     */
    Query getQuery(String queryString, int pageIndex, int pageSize, Object param, int type, Session session,
            Class<?> entity);

    /* 
     * 简述
     * <p>详细说明第一行<br>    
     * 详细说明第二行 
     * @date 2015年4月1日 上午9:13:57   
     * @author Eric   
     * @param query
     * @param pageIndex
     * @param pageSize 
     * @see com.lenovo.rms.common.dao.impl.Iterfa#setPageResult(org.hibernate.Query, int, int) 
     */
    void setPageResult(Query query, int pageIndex, int pageSize);

}


