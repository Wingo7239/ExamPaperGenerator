package com.yw.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET,produces = { "application/json;charset=utf-8" })
	@ResponseBody
	public boolean login(HttpServletResponse response){
		Cookie cookie = new Cookie("UID", "10000");  
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);  
		return true;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET,produces = { "application/json;charset=utf-8" })
	public String test(@CookieValue(value = "token", required = false) String cookie,Model model){
		model.addAttribute("token", cookie);
		return "test";
	}
	
}
