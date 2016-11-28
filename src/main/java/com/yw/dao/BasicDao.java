package com.yw.dao;

import java.io.Serializable;
import java.util.List;

public interface BasicDao {
	/**
	 * query by id
	 * @param clazz
	 * @param id
	 * @return object
	 */
	public Object findById(Class clazz, java.io.Serializable id);

	/**
	 * execute hql query
	 * @param hql
	 * @param parameters
	 * @return result list
	 */
	public List executeQurey(String hql, Object[] parameters);
	
	/**
	 * execute hql update
	 * @param hql
	 * @param parameters
	 * @return number of rows affected
	 */
	public int executeUpdate(String hql, Object[] parameters);
	
	/**query by page
	 * @param hql
	 * @param parameters
	 * @param pageNow
	 * @param pageSize
	 * @return result list
	 */
	public List executeQueryByPage(String hql, Object[] parameters, int pageNow, int pageSize);

	/**add
	 * @param object
	 * @return id
	 */
	public Serializable add(Object object);

	/**update object
	 * @param object
	 */
	public void updateObject(Object object);

	/**delete object
	 * @param object
	 */
	public void deleteObject(Object object);

	/** Single result query
	 * @param hql
	 * @param parameters
	 * @return Object
	 */
	public Object uniqueQuery(String hql, Object[] parameters);

	/** getPageCount
	 * @param hql
	 * @param parameters
	 * @param pageSize
	 * @return PageCount
	 */
	public int getPageCount(String hql, Object[] parameters, int pageSize);
}
