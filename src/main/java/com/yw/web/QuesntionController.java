package com.yw.web;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yw.domain.Category;
import com.yw.domain.Question;
import com.yw.dto.Problem;
import com.yw.enums.QuestionTypeEnum;
import com.yw.service.CategoryService;
import com.yw.service.QuestionService;

@Controller
@RequestMapping("")
public class QuesntionController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private QuestionService questionService;
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/{subjectId}/list", method = RequestMethod.GET)
	public String list(@PathVariable("subjectId") String subjectId, Model model) {
		System.out.println("*****************************************************");
		List<Question> list = questionService.getQuestionListByPage(1, 15, "", subjectId, "", "");

		// 图片加前缀,之后改数据库进行或者下载到本地
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
		model.addAttribute("subjectId", subjectId);
		model.addAttribute("list", list);
		model.addAttribute("type", QuestionTypeEnum.values());

		List<Category> tmp = categoryService.getSubcategoryList(subjectId);
		for (Category c : tmp) {
			if (c.isIsFolder())
				model.addAttribute(c.getId(), categoryService.getSubcategoryList(c.getId()));
		}
		model.addAttribute(subjectId, tmp);

		return "list";
	}

	@RequestMapping(value = "/question", method = RequestMethod.GET, produces = { "application/json;charset=utf-8" })
	@ResponseBody
	public List<Problem> question(@RequestParam("pageNow") String pageNow, @RequestParam("pageSize") String pageSize,
			@RequestParam("knowledge") String knol, @RequestParam("type") String type,
			@RequestParam("search") String search) {
		int pn = 1, ps = 15;
		if (pageNow != null && pageNow != "") {
			pn = Integer.parseInt(pageNow);
		}
		if (pageSize != null && pageSize != "") {
			ps = Integer.parseInt(pageSize);
		}
		List<Question> tmp = questionService.getQuestionListByPage(pn, ps, type, knol, "", search);
		List<Problem> list = new ArrayList<Problem>();
		for (Question q : tmp) {
			q.setCategory(categoryService.getNameById(q.getCategory()));
			list.add(new Problem(q));
		}

		return list;

	}

	@RequestMapping(value = "/pageCount", method = RequestMethod.GET, produces = { "application/json;charset=utf-8" })
	@ResponseBody
	public int pageCount(@RequestParam("pageNow") String pageNow, @RequestParam("pageSize") String pageSize,
			@RequestParam("knowledge") String knol, @RequestParam("type") String type,
			@RequestParam("search") String search) {

		return questionService.getQuestionPageCount(Integer.parseInt(pageSize), type, knol, "", search);

	}
}
