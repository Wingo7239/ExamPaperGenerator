package com.yw.service;

import java.util.List;

import com.yw.domain.Category;

public interface CategoryService {

	/**
	 * 按科目查询知识点
	 * @param id
	 * @return List<Category>
	 */
	List<Category> getCategoryBySubject(String id);
	
	/**
	 * 添加知识点
	 * @param id
	 * @param Name
	 * @param isFolder
	 * @return
	 */
	boolean insertCategory(String id, String Name, boolean isFolder);
	
	/**
	 * 删除知识点
	 * @return
	 */
	boolean deleteCategory(String id);
	
	/**
	 * 获取子知识点列表
	 * @param id
	 * @return
	 */
	List<Category> getSubcategoryList(String id);
	
	String getNameById(String Id);
	
}
