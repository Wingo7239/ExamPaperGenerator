package com.yw.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yw.dao.CategoryDao;
import com.yw.dao.impl.BasicDaoImpl;
import com.yw.domain.Category;


@Repository("categoryDao")
public class CategoryDaoImpl extends BasicDaoImpl implements CategoryDao{

	public List findBySubject(String id) {
		// TODO Auto-generated method stub
		
		String sql = "from Category where Id like ?";
		Object[] param = {id+"%"};
		return executeQurey(sql, param);
	}

}
