package com.yw.service.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yw.dao.PaperDao;
import com.yw.domain.Paper;
import com.yw.enums.ChineseNoEnum;
import com.yw.service.PaperService;
import com.yw.service.QuestionService;

@Service
@Transactional
public class PaperServiceImpl implements PaperService{

			
	@Autowired
	private PaperDao paperDao;
	@Autowired
	private QuestionService questionService;
	
	public String generatePaper(int id) {
		// TODO Auto-generated method stub
		Paper meta = (Paper)paperDao.findById(Paper.class, id);
		
		ObjectMapper mapper = new ObjectMapper();
		StringBuilder sb = new StringBuilder();
		StringBuffer sb2 = new StringBuffer();
		String line = null;
		int no = 1;
		int No = 1;
		try {
			HashMap<String, Object> tmp =  mapper.readValue(meta.getPaper(), HashMap.class);
			List<LinkedHashMap<String, Object>> grouplist = (List<LinkedHashMap<String, Object>>) tmp.get("grouplist");
			for(LinkedHashMap<String, Object> map : grouplist){
				sb.append("<div class=\"questypehead\"><div class=\"questypetitle\">"+ChineseNoEnum.getName(No++)+map.get("title")+"</div></div>");
				for(String i : (List<String>)map.get("idlist")){
					sb.append("<div class=\"ques\">");
					String[] str = questionService.getById(Integer.parseInt(i)).getMemo().split(">",2);
					sb.append(str[0]);
					sb.append("><span><b>"+no+++".</b></span>");
					sb.append(str[1]);
					sb.append("</div>");
				}
			}
			
			
			InputStream fis = new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("/").getPath()+"/temp.html");
			InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			BufferedReader br = new BufferedReader(isr);
			
			while ((line = br.readLine()) != null) {
				sb2.append(line);
			}
			
			line = MessageFormat.format(sb2.toString(), tmp.get("maintitle"),tmp.get("subtitle"),tmp.get("testinfo"),sb.toString());
			
			
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
		return line;
	}

	public int insertPaper(String uid, String paper) {
		// TODO Auto-generated method stub
		return (Integer) paperDao.add(new Paper(uid, paper));
	}

	public boolean deletePaper(int id) {
		// TODO Auto-generated method stub
		paperDao.deleteObject(new Paper(id));
		return true;
	}

}
