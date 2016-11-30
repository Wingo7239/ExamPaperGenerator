package com.yw.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yw.domain.Question;
import com.yw.service.KnowledgeService;
import com.yw.service.QuestionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring_*.xml"})

public class QuestionServiceTest {

	@Autowired
	private QuestionService questionService;
	
	@Test
	@Transactional
	public void testGetQuestionListByPage(){
		List<Question> list = questionService.getQuestionListByPage(1, 15, "", "09", "", "");
		for(Question q : list){
			System.out.println(q.getKnowledge().getId()+" : "+q.getKnowledge().getId());
		}
	}
	

	
	@Test
	@Transactional
	public void testGetQuestionPageCount(){
		System.out.println(questionService.getQuestionPageCount(15, "", "", "", ""));
		
	}
	
	
	
	

}
