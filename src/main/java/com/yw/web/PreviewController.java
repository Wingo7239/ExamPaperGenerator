package com.yw.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yw.domain.Knowledge;
import com.yw.domain.Question;
import com.yw.dto.Cart;
import com.yw.enums.QuestionTypeEnum;
import com.yw.service.CartService;
import com.yw.service.QuestionService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("")
public class PreviewController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private QuestionService questionService;
	@Autowired
	private CartService cartService;

	@RequestMapping(value = "/preview", method = RequestMethod.GET)
	public String addToCart(@CookieValue(value = "JSESSIONID", required = false) String sid,
			@CookieValue(value = "UID", required = false) String uid, Model model) {

		
//		String cartStr = (uid == null?cartService.getCart(sid):cartService.getCart(uid));
		
		String cartStr = "[{\"count\":2,\"name\":\"填空题\",\"queslist\":[1,2]},{\"count\":2,\"name\":\"计算题\",\"queslist\":[3,4]}]";
		
		
		
		
		ArrayList<Cart> cart = new ArrayList<Cart>();
		ArrayList<String> titleList = new ArrayList<String>();

		if (cartStr == null) {
			return "error";
		} else {
			ObjectMapper mapper = new ObjectMapper();

			try {
				
				List<LinkedHashMap<String, Object>> list = mapper.readValue(cartStr, List.class);
		        System.out.println(list.size());
		        for (int i = 0; i < list.size(); i++) {
		            Map<String, Object> map = list.get(i);
		            ArrayList<Integer> queslist = (ArrayList<Integer>) map.get("queslist");
		            ArrayList<Question> res = new ArrayList<Question>();
		            for(Integer n : queslist){
		            	res.add(questionService.getById(n));
		            }
		            Cart tmp = new Cart((String)map.get("name"), (Integer)map.get("count"), res);
		            cart.add(tmp);
		            if(tmp.getName().equals("选择题")||tmp.getName().equals("填空题")||tmp.getName().equals("判断题")||tmp.getName().equals("实验题")){
		            	titleList.add("每小题0分，共0分");
		            }
		            else{
		            	String str = "";
		            	for(int j = 1 ; j <tmp.getCount() ; j++){
		            		str +="第"+j+"题0分，";
		            	}
		            	str +="第"+tmp.getCount()+"题0分，共0分";
		            	titleList.add(str);
		            }
		            
		        }
				model.addAttribute("cart",cart);
				model.addAttribute("titleList",titleList);

			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return "preview";
	}
}
