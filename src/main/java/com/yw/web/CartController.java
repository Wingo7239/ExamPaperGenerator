package com.yw.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yw.domain.Question;
import com.yw.dto.Cart;
import com.yw.service.CartService;
import com.yw.service.QuestionService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("")
public class CartController {

	@Autowired
	private CartService cartService;

	@RequestMapping(value = "/cart", method = RequestMethod.POST, produces = { "application/json;charset=utf-8" })
	@ResponseBody
	public boolean addToCart(@CookieValue(value = "JSESSIONID", required = false) String sid,
			@CookieValue(value = "UID", required = false) String uid, @RequestBody String list) {
		try {
			list = URLDecoder.decode(list, "utf8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		list = list.substring(0, list.length()-1);

		StringBuilder sb = new StringBuilder("[");
		Pattern p = Pattern.compile("\\{\"count\":(\\d*),(.*?)\\}");
		Matcher m = p.matcher(list);
		while(m.find()){
			if(Integer.parseInt(m.group(1)) != 0){
			sb.append(m.group(0)+",");
			}
		}
		sb.setCharAt(sb.length()-1, ']');
		
		if (uid != null) {
			cartService.insertCart(uid, sb.toString());
		} else if (sid != null) {
			cartService.insertCart(sid, sb.toString());
		} else
			return false;
		return true;
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET, produces = { "application/json;charset=utf-8" })
	@ResponseBody
	public String getCart(@CookieValue(value = "JSESSIONID", required = false) String sid,
			@CookieValue(value = "UID", required = false) String uid) {

		if (uid != null) {
			return cartService.getCart(uid);
		} else if (sid != null) {
			return cartService.getCart(sid);
		}
		return "[]";

		// return
		// "[{\"count\":2,\"name\":\"填空题\",\"queslist\":[1,2]},{\"count\":2,\"name\":\"计算题\",\"queslist\":[3,4]}]";
	}

}
