package com.yw.dao;

import java.util.List;

import com.yw.domain.Category;

public interface CategoryDao extends BasicDao {

	
	public List<Category> findBySubject(String id);
}
