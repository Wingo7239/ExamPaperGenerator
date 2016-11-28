package com.yw.service;

import java.util.List;

import com.yw.domain.Question;

public interface QuestionService {
	
	/**
	 * 
	 * @param PageSize
	 * @param type
	 * @param knol
	 * @param year
	 * @param search
	 * @return
	 */
	
	int getQuestionPageCount(int PageSize, String type, String knol, String year, String search);

	/**
	 * 分页查询题目
	 * @param pageNow
	 * @param PageSize
	 * @param type
	 * @param knol
	 * @param year
	 * @param search
	 * @return
	 */
	List<Question> getQuestionListByPage(int pageNow, int pageSize, String type, String knol, String year, String search);
	
	
	/**
	 * 添加题目
	 * @param memo
	 * @param answer
	 * @param source
	 * @param year
	 * @param Category
	 * @return
	 */
	int insertQuestion(String type,String memo,String answer, String source, String year, String Category);
	
	/**
	 * 删除题目
	 * @param id
	 * @return
	 */
	boolean deleteQuestion(int id);
}
