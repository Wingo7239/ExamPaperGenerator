package com.yw.web;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
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
			@CookieValue(value = "UID", required = false) String uid,@RequestParam("list") String list) {
		if(uid != null){
			cartService.insertCart(uid, list);
		}
		else if(sid != null){
			cartService.insertCart(sid, list);
		}
		else
			return false;
		return true;
	}
	
	@RequestMapping(value = "/cart", method = RequestMethod.GET, produces = { "application/json;charset=utf-8" })
	@ResponseBody
	public String getCart(@CookieValue(value = "JSESSIONID", required = false) String sid,
			@CookieValue(value = "UID", required = false) String uid) {
		if(uid != null){
			return cartService.getCart(uid);
		}
		else if(sid != null){
			return cartService.getCart(sid);
		}
		return "";
	}

}
