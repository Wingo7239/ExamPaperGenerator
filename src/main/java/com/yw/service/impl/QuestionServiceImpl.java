package com.yw.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yw.dao.CategoryDao;
import com.yw.dao.QuestionDao;
import com.yw.domain.Question;
import com.yw.enums.QuestionTypeEnum;
import com.yw.service.QuestionService;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
	

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private QuestionDao questionDao;

	

	public int insertQuestion(String type, String memo, String answer, String source, String year, String Category) {
		// TODO Auto-generated method stub
		return (Integer) questionDao.add(new Question(QuestionTypeEnum.idOf(type).getId(), memo, answer, source, year, Category));
	}

	public boolean deleteQuestion(int id) {
		// TODO Auto-generated method stub
		questionDao.deleteObject(new Question(id));
		return true;
	}


	public int getQuestionPageCount(int pageSize, String type, String knol, String year, String search) {
		// TODO Auto-generated method stub
		String hql = "from Question as q  ";
		if(type != null && type != ""){
			hql += "where q.type= '"+type+"'";
		}
		if(year != null && year != ""){
			hql += " AND q.year= "+year;
		}
		if(knol != null && knol != ""){
			hql += " AND q.category like '"+knol+"%'";
		}
		if(search != null && search !=""){
			hql += " AND q.memo like %" +search+"%";
		}
		
		return questionDao.getPageCount(hql, null, pageSize);
	}



	public List<Question> getQuestionListByPage(int pageNow, int pageSize, String type, String knol, String year,
			String search) {
		// TODO Auto-generated method stub
		String hql = "from Question as q  ";
		boolean flag = false;
		if(type != null && type != ""){
			if(!flag){
				hql += " where ";
				flag = true;
				}
			else{
				hql += " AND ";
			}
			hql += "q.type= '"+type+"'";
		}
		if(year != null && year != ""){
			if(!flag){
				hql += " where ";
				flag = true;
				}
			else{
				hql += " AND ";
			}
			hql += "q.year= "+year;
		}
		if(knol != null && knol != ""){
			if(!flag){
				hql += " where ";
				flag = true;
				}
			else{
				hql += " AND ";
			}
			hql += "q.category like '"+knol+"%'";
		}
		if(search != null && search !=""){
			if(!flag){
				hql += " where ";
				flag = true;
				}
			else{
				hql += " AND ";
			}
			hql += "q.memo like %" +search+"%";
		}
		return questionDao.executeQueryByPage(hql, null, pageNow, pageSize);
	}

	

}
