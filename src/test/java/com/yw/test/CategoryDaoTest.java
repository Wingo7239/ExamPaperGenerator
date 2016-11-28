package com.yw.test;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yw.dao.CategoryDao;
import com.yw.dao.QuestionDao;
import com.yw.domain.Category;
import com.yw.domain.Question;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring_*.xml"})

public class CategoryDaoTest {

	@Resource
	private CategoryDao categoryDao;
	
	@Resource
	private QuestionDao questionDao;

	@Test
	@Transactional
	public void testFindBySubject() {
		List<Category> list = categoryDao.findBySubject("09");
		for(Category c : list){
			System.out.println(c.getId()+c.getName());
		}
	}
	
}
