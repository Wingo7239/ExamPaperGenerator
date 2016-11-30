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
import com.yw.service.QuestionService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("")
public class CartController {
	
	@Autowired
	private QuestionService questionService;
	
	
	
	@RequestMapping(value = "/cart", method = RequestMethod.GET, produces = { "application/json;charset=utf-8" })
	@ResponseBody
	public ArrayList<Cart> addToCart(@CookieValue(value = "cart",required  = false) String cartStr,@RequestParam("type") int type,@RequestParam("ques") String qid){
		
		ArrayList<Cart> cart = new ArrayList<Cart>();
		
		if(cartStr == null){
			
		}else{
			ObjectMapper mapper = new ObjectMapper();
			
			try {
				Cart[] list= mapper.readValue(cartStr, Cart[].class);
				for(Cart cd : list){
					ArrayList<Question> quesList = cd.getQuesList();
					for(int i = 0 ; i < quesList.size();i++){
						quesList.set(i, questionService.getById(quesList.get(i).getId()));
					}
					cart.add(new Cart(cd.getName(), cd.getCount(), quesList));
				}
				
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

		return cart;	
	}
	
	
	
}
