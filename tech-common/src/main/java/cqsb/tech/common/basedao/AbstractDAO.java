package cqsb.tech.common.basedao;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import cqsb.tech.common.page.Page;
import cqsb.tech.common.utils.GenericsUtils;

public abstract class AbstractDAO<T> extends HibernateDaoSupport implements BaseDao<T>{
	
	//T对象实例
	protected Class<?> entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());
	/**
	 * 获取记录总数
	 * @return
	 */
	public long getCount(){
		return (Long) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Long doInHibernate(Session session)
					throws HibernateException {
				return (Long) session.createQuery("select count("+ getCountField(AbstractDAO.this.entityClass) +") from "+ getEntityName(AbstractDAO.this.entityClass)+ " o").uniqueResult();
				
			}
		});
	}
	
	/**
	 * 保存实体
	 * @param entity 实体id
	 */
	public void save(T entity){
		getHibernateTemplate().persist(entity);
	}
	
	/**
	 * 更新实体
	 * @param entity 实体id
	 */
	public boolean update(T entity){
		 try{
			 	getHibernateTemplate().merge(entity);
	        }catch (Exception e){
	            return false;
	        }
	        return  true;
	}
	
	/**
	 * 删除实体
	 * @param entityids 实体id数组
	 */
	public void delete(Serializable... entityids){
		for(Serializable id : entityids){
			Object clazz = getHibernateTemplate().get(this.entityClass, id );
			if(clazz != null){
				getHibernateTemplate().delete(getHibernateTemplate().get(this.entityClass, id));
			}
		}
	}
	/**
	 * 获取实体
	 * @param entityId 实体id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public T find(Serializable entityId){
		if(entityId==null) 
			throw new RuntimeException(this.entityClass.getName()+ ":传入的实体id不能为空");
		return  (T) getHibernateTemplate().get(this.entityClass, entityId);
	}
	
	/**
	 * 根据条件分页查询，结果根据条件排序
	 * @param firstindex 开始查询位置从0开始
	 * @param maxresult 一页的最大记录数
	 * @param wherejpql 查询条件  "o.email=? and o.account like ?"
	 * @param queryParams  查询条件占位符对应的参数值，
	 * @param orderby 排序条件  Key为属性,Value为asc/desc
	 * @return 查询结果类
	 */
	public abstract Page<T> getPageData(int firstindex, int maxresult, String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby);
	/**
	 * 根据条件分页查询
	 * @param firstindex 开始查询位置从0开始
	 * @param maxresult 一页的最大记录数
	 * @param wherejpql 查询条件  "o.email=? and o.account like ?"
	 * @param queryParams  查询条件占位符对应的参数值，
	 * @return 查询结果类
	 */
	public abstract Page<T> getPageData(int firstindex, int maxresult, String wherejpql, Object[] queryParams);
	/**
	 * 分页查询，结果根据条件排序
	 * @param firstindex 开始查询位置从0开始
	 * @param maxresult 一页的最大记录数
	 * @param orderby 排序条件  Key为属性,Value为asc/desc
	 * @return 查询结果类
	 */
	public abstract Page<T> getPageData(int firstindex, int maxresult, LinkedHashMap<String, String> orderby);
	/**
	 * 分页查询
	 * @param firstindex 开始查询位置从0开始
	 * @param maxresult 一页的最大记录数
	 * @return 查询结果类
	 */
	public abstract Page<T> getPageData(int firstindex, int maxresult);
	/**
	 * 根据条件查询所有数据
	 * @param wherejpql 查询条件  "o.email=? and o.account=?"
	 * @param queryParams 查询条件占位符对应的参数值，
	 */
	public abstract List<T> getAllData(String wherejpql, Object[] queryParams);

	/**
	 * 根据条件查询，结果根据条件排序
	 * @param wherejpql 查询条件  "o.email=? and o.account=?"
	 * @param queryParams 查询条件占位符对应的参数值，
	 * @param orderby 排序条件  Key为属性,Value为asc/desc
	 */
	public abstract List<T> getAllData(final String wherejpql, final Object[] queryParams, final LinkedHashMap<String, String> orderby);
	/**
	 * 查询所有数据
	 * @return
	 */
	public abstract List<T> getAllData();
	
	/**
	 * 获取统计属性,该方法是为了解决hibernate解析联合主键select count(o) from Xxx o语句BUG而增加,hibernate对此jpql解析后的sql为select count(field1,field2,...),显示使用count()统计多个字段是错误的
	 * @param <E>泛型方法声明
	 * @param clazz
	 * @return
	 */
	protected static <T> String getCountField(Class<T> clazz){
		String out = "o";
		try {
			PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
			for(PropertyDescriptor propertydesc : propertyDescriptors){
				Method method = propertydesc.getReadMethod();
				if(method!=null && method.isAnnotationPresent(EmbeddedId.class)){					
					PropertyDescriptor[] ps = Introspector.getBeanInfo(propertydesc.getPropertyType()).getPropertyDescriptors();
					out = "o."+ propertydesc.getName()+ "." + (!ps[1].getName().equals("class")? ps[1].getName(): ps[0].getName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return out;
	}
	
	/**
	 * 获取实体的名称
	 * @param <E>泛型方法声明
	 * @param clazz 实体类
	 * @return
	 */
	protected static <T> String getEntityName(Class<T> clazz){
		String entityname = clazz.getSimpleName();
		Entity entity = clazz.getAnnotation(Entity.class);
		if(entity.name()!=null && !"".equals(entity.name())){
			entityname = entity.name();
		}
		return entityname;
	}
}
