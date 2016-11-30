package com.yw.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yw.dao.KnowledgeDao;
import com.yw.domain.Knowledge;
import com.yw.service.KnowledgeService;

@Service
@Transactional
public class KnowledgeServiceImpl implements KnowledgeService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private KnowledgeDao knowledgeDao;
	
	public List<Knowledge> getKnowledgeBySubject(String id) {
		// TODO Auto-generated method stub
		return knowledgeDao.findBySubject(id);
	}

	public boolean insertKnowledge(String id, String name, boolean isFolder) {
		// TODO Auto-generated method stub
		knowledgeDao.add(new Knowledge(id, name, isFolder));
		return true;
	}

	public boolean deleteKnowledge(String id) {
		// TODO Auto-generated method stub
		knowledgeDao.deleteObject(new Knowledge(id));
		return false;
	}

	public List<Knowledge> getSubcategoryList(String id) {
		// TODO Auto-generated method stub
		String hql = "from Knowledge where Id like '"+id+"__'";
		return knowledgeDao.executeQurey(hql, null);
	}

	public String getNameById(String Id) {
		// TODO Auto-generated method stub
		return ((Knowledge) knowledgeDao.findById(com.yw.domain.Knowledge.class, Id)).getName();
	}

}
