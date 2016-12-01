package com.yw.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

import com.yw.domain.Knowledge;
import com.yw.domain.Question;
import com.yw.enums.QuestionTypeEnum;
import com.yw.service.KnowledgeService;
import com.yw.service.QuestionService;

@Controller
@RequestMapping("")
public class QuesntionController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private QuestionService questionService;
	@Autowired
	private KnowledgeService knowlegdeService;

	@RequestMapping(value = "/{subjectId}/list", method = RequestMethod.GET)
	public String list(@PathVariable("subjectId") String subjectId, Model model) {
		List<Question> list = questionService.getQuestionListByPage(10, 15, "", subjectId, "", "");

		model.addAttribute("subjectId", subjectId);
		model.addAttribute("list", list);
		model.addAttribute("type", QuestionTypeEnum.values());

		List<Knowledge> tmp = knowlegdeService.getSubcategoryList(subjectId);
		for (Knowledge c : tmp) {
			if (c.isIsFolder())
				model.addAttribute(c.getId(), knowlegdeService.getSubcategoryList(c.getId()));
		}
		model.addAttribute(subjectId, tmp);

		return "list";
	}

	@RequestMapping(value = "/question", method = RequestMethod.GET, produces = { "application/json;charset=utf-8" })
	@ResponseBody
	public Map<String,Object> question(@RequestParam("pageNow") String pageNow, @RequestParam("pageSize") String pageSize,
			@RequestParam("knowledge") String knol, @RequestParam("type") String type,
			@RequestParam("search") String search) {
		int pn = 1, ps = 15;
		if (pageNow != null && pageNow != "") {
			pn = Integer.parseInt(pageNow);
		}
		if (pageSize != null && pageSize != "") {
			ps = Integer.parseInt(pageSize);
		}
		List<Question> list = questionService.getQuestionListByPage(pn, ps, type, knol, "", search);
		
		HashMap<String,Object> res = new HashMap();
		res.put("pageCount", questionService.getQuestionPageCount(ps, type, knol, "", search));
		res.put("list", list);
		return res;

	}
}
