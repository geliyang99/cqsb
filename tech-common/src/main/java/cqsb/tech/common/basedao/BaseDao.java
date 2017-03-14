package cqsb.tech.common.basedao;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import cqsb.tech.common.page.Page;

public interface BaseDao<T> {

	/**
	 * 获取记录总数
	 * @return
	 */
	public long getCount();
	
	/**
	 * 保存实体
	 * @param entity 实体id
	 */
	public void save(T entity);
	/**
	 * 更新实体
	 * @param entity 实体id
	 */
	public boolean update(T entity);
	/**
	 * 删除实体
	 * @param entityids 实体id数组
	 */
	public void delete(Serializable... entityids);
	/**
	 * 获取实体
	 * @param entityId 实体id
	 * @return
	 */
	public T find(Serializable entityId);
	
	/**
	 * 根据条件分页查询，结果根据条件排序
	 * @param firstindex 开始查询位置从0开始
	 * @param maxresult 一页的最大记录数
	 * @param wherejpql 查询条件  "o.email=? and o.account like ?"
	 * @param queryParams  查询条件占位符对应的参数值，
	 * @param orderby 排序条件  Key为属性,Value为asc/desc
	 * @return 查询结果类
	 */
	public Page<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby);
	/**
	 * 根据条件分页查询
	 * @param firstindex 开始查询位置从0开始
	 * @param maxresult 一页的最大记录数
	 * @param wherejpql 查询条件  "o.email=? and o.account like ?"
	 * @param queryParams  查询条件占位符对应的参数值，
	 * @return 查询结果类
	 */
	public Page<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams);
	/**
	 * 分页查询，结果根据条件排序
	 * @param firstindex 开始查询位置从0开始
	 * @param maxresult 一页的最大记录数
	 * @param orderby 排序条件  Key为属性,Value为asc/desc
	 * @return 查询结果类
	 */
	public Page<T> getScrollData(int firstindex, int maxresult, LinkedHashMap<String, String> orderby);
	/**
	 * 分页查询
	 * @param firstindex 开始查询位置从0开始
	 * @param maxresult 一页的最大记录数
	 * @return 查询结果类
	 */
	public Page<T> getScrollData(int firstindex, int maxresult);
	/**
	 * 根据条件查询所有数据
	 * @param wherejpql 查询条件  "o.email=? and o.account=?"
	 * @param queryParams 查询条件占位符对应的参数值，
	 */
	public List<T> getAllData(String wherejpql, Object[] queryParams);

	/**
	 * 根据条件查询，结果根据条件排序
	 * @param wherejpql 查询条件  "o.email=? and o.account=?"
	 * @param queryParams 查询条件占位符对应的参数值，
	 * @param orderby 排序条件  Key为属性,Value为asc/desc
	 */
	public List<T> getAllData(final String wherejpql, final Object[] queryParams, final LinkedHashMap<String, String> orderby);
	/**
	 * 查询所有数据
	 * @return
	 */
	public List<T> getAllData();
}
