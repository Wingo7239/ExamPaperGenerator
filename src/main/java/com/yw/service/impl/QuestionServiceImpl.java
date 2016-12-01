package com.yw.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yw.dao.KnowledgeDao;
import com.yw.dao.QuestionDao;
import com.yw.domain.Question;
import com.yw.domain.Knowledge;
import com.yw.enums.QuestionTypeEnum;
import com.yw.service.QuestionService;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
	

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private QuestionDao questionDao;

	

	public int insertQuestion(String type, String memo, String answer, String source, String year, String Knowledge) {
		// TODO Auto-generated method stub
		return (Integer) questionDao.add(new Question(new Knowledge(Knowledge), Integer.parseInt(type), memo, answer, source, year));
	}

	public boolean deleteQuestion(int id) {
		// TODO Auto-generated method stub
		questionDao.deleteObject(new Question(id));
		return true;
	}


	public int getQuestionPageCount(int pageSize, String type, String knol, String year, String search) {
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
			hql += "q.knowledge like '"+knol+"%'";
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
			hql += "q.knowledge like '"+knol+"%'";
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
		
		List<Question> res = questionDao.executeQueryByPage(hql, null, pageNow, pageSize);
		getImageUrl(res);
		return  res;
	}

	public Question getById(int id) {
		// TODO Auto-generated method stub
		
		List<Question> res = new ArrayList<Question>();
		res.add((Question) questionDao.findById(Question.class, id));
		return res.get(0);
		
	}

	
	// 图片加前缀,之后改数据库进行或者下载到本地
	public void getImageUrl(List<Question> list){
		Pattern p = Pattern.compile("(<img.*?src=')(.*?)'/>");
		for (Question q : list) {
			Matcher m = p.matcher(q.getMemo());
			StringBuffer sb = new StringBuffer();
			while (m.find()) {
				m.appendReplacement(sb, m.group(1) + "http://zujuan.ks5u.com" + m.group(2) + "'./>");
			}
			m.appendTail(sb);
			q.setMemo(sb.toString());

			m = p.matcher(q.getAnswer());
			sb = new StringBuffer();
			while (m.find()) {
				m.appendReplacement(sb, m.group(1) + "http://zujuan.ks5u.com" + m.group(2) + "'./>");
			}
			m.appendTail(sb);
			q.setAnswer(sb.toString());
		}
	}

}
