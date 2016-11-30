package com.yw.dao;

import java.util.List;

import com.yw.domain.Knowledge;

public interface KnowledgeDao extends BasicDao {

	
	public List<Knowledge> findBySubject(String id);
}
