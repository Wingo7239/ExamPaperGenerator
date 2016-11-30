package com.yw.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yw.dao.KnowledgeDao;
import com.yw.dao.impl.BasicDaoImpl;
import com.yw.domain.Knowledge;


@Repository("knowledgeDao")
public class KnowledgeDaoImpl extends BasicDaoImpl implements KnowledgeDao{

	public List findBySubject(String id) {
		// TODO Auto-generated method stub
		
		String sql = "from Knowledge where Id like ?";
		Object[] param = {id+"%"};
		return executeQurey(sql, param);
	}

}
