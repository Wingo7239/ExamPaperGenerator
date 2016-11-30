package com.yw.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yw.domain.Knowledge;
import com.yw.service.KnowledgeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring_*.xml"})
public class KnowledgeServiceTest {
	
	@Autowired
	private KnowledgeService knowledgeService;

	@Test
	@Transactional
	public void testGetCategoryBySubject(){
		List<Knowledge> list= knowledgeService.getKnowledgeBySubject("090101");
		for(Knowledge c : list){
			System.out.println(c.getId()+c.getName());
		}
	}
	
	@Test
	@Transactional
	public void testInsertCategory(){
		knowledgeService.insertKnowledge("01", "语文", true);
	}
}
