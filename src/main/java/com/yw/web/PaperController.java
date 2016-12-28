package com.yw.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yw.service.PaperService;
import com.yw.util.Html2Word;

@Controller
@RequestMapping("")
public class PaperController {

	@Autowired
	private PaperService paperService;

	@RequestMapping(value = "/paper", method = RequestMethod.POST, produces = { "application/json;charset=utf-8" })
	@ResponseBody
	public String savePaper(@CookieValue(value = "JSESSIONID", required = false) String sid,
			@CookieValue(value = "UID", required = false) String uid, @RequestBody(required = false) String list) {

//		list = "{\"maintitle\": \"曲靖一中期中考试\", \"subtitle\": \"高二年级物理试卷\", \"testinfo\": \"命题人：李昆华\", \"grouplist\": [{\"title\": \"一、填空题（2道小题）\", \"queslist\": [11, 12]}, {\"title\": \"二、计算题（2道小题）\", \"queslist\": [23, 24]}]}";

		list = list.substring(0, list.length()-1);
		try {
			System.out.println(list);
			list = URLDecoder.decode(list, "utf8");
			System.out.println(list);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (uid != null) {
			// save to database
			return paperService.insertPaper(uid, list) + "";
		} else if (sid != null) {
			return paperService.insertPaper(sid, list) + "";
		}

		return "false";
	}

	@RequestMapping(value = "/paper", method = RequestMethod.GET)

	public ResponseEntity<byte[]> getPaper(@CookieValue(value = "JSESSIONID", required = false) String sid,
			@CookieValue(value = "UID", required = false) String uid, @RequestParam("pid") int pid) {

		// todo:验证用户是否正确
		String paper = paperService.generatePaper(pid);

		
		Pattern p = Pattern.compile("<div\\sid=\"pui_maintitle\"\\stitle=\"试卷主标题\">(.*?)</div>");
		Matcher m = p.matcher(paper);
		m.find();
		String fileName = "["+pid+"]"+m.group(1)+".doc";
		String dfileName = "";
		try {
			dfileName = new String(fileName.getBytes("utf8"), "iso8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", dfileName);
		return new ResponseEntity<byte[]>(paper.getBytes(), headers, HttpStatus.CREATED);

	}
}
