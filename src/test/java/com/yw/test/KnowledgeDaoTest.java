package com.yw.test;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yw.dao.KnowledgeDao;
import com.yw.dao.QuestionDao;
import com.yw.domain.Knowledge;
import com.yw.domain.Question;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring_*.xml"})

public class KnowledgeDaoTest {

	@Resource
	private KnowledgeDao knowledgeDao;
	
	@Resource
	private QuestionDao questionDao;

	@Test
	@Transactional
	public void testFindBySubject() {
		List<Knowledge> list = knowledgeDao.findBySubject("09");
		for(Knowledge c : list){
			System.out.println(c.getId()+c.getName());
		}
	}
	
}
