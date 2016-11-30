package com.yw.service;

import java.util.List;

import com.yw.domain.Knowledge;

public interface KnowledgeService {

	/**
	 * 按科目查询知识点
	 * @param id
	 * @return List<Category>
	 */
	List<Knowledge> getKnowledgeBySubject(String id);
	
	/**
	 * 添加知识点
	 * @param id
	 * @param Name
	 * @param isFolder
	 * @return
	 */
	boolean insertKnowledge(String id, String Name, boolean isFolder);
	
	/**
	 * 删除知识点
	 * @return
	 */
	boolean deleteKnowledge(String id);
	
	/**
	 * 获取子知识点列表
	 * @param id
	 * @return
	 */
	List<Knowledge> getSubcategoryList(String id);
	
	String getNameById(String Id);
	
}
