package com.yw.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yw.dao.CategoryDao;
import com.yw.domain.Category;
import com.yw.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CategoryDao categoryDao;
	
	public List<Category> getCategoryBySubject(String id) {
		// TODO Auto-generated method stub
		return categoryDao.findBySubject(id);
	}

	public boolean insertCategory(String id, String name, boolean isFolder) {
		// TODO Auto-generated method stub
		categoryDao.add(new Category(id, name, isFolder));
		return true;
	}

	public boolean deleteCategory(String id) {
		// TODO Auto-generated method stub
		categoryDao.deleteObject(new Category(id));
		return false;
	}

	public List<Category> getSubcategoryList(String id) {
		// TODO Auto-generated method stub
		String hql = "from Category where Id like '"+id+"__'";
		return categoryDao.executeQurey(hql, null);
	}

	public String getNameById(String Id) {
		// TODO Auto-generated method stub
		return ((Category) categoryDao.findById(com.yw.domain.Category.class, Id)).getName();
	}

}
