package com.yw.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yw.domain.Category;
import com.yw.service.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring_*.xml"})
public class CategoryServiceTest {
	
	@Autowired
	private CategoryService categoryService;

	@Test
	@Transactional
	public void testGetCategoryBySubject(){
		List<Category> list= categoryService.getCategoryBySubject("090101");
		for(Category c : list){
			System.out.println(c.getId()+c.getName());
		}
	}
	
	@Test
	@Transactional
	public void testInsertCategory(){
		categoryService.insertCategory("01", "语文", true);
	}
}
