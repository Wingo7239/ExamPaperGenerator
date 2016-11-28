package com.yw.dto;

import com.yw.domain.Question;
import com.yw.enums.QuestionTypeEnum;

public class Problem {
	
	private int id;
	private int type_id;
	private String type_name;
	private String memo;
	private String answer;
	private String source;
	private String knowledge;
	
	
	
	
	public Problem(){}
	
	
	
	
	
	public Problem(Question q) {
		// TODO Auto-generated constructor stub
		this.id = q.getId();
		this.setType_id(q.getType());
		this.setType_name(QuestionTypeEnum.nameOf(q.getType()).getName());
		this.memo = q.getMemo();
		this.answer = q.getAnswer();
		this.source = q.getSource();
		this.knowledge = q.getCategory();
	}




	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}





	public int getType_id() {
		return type_id;
	}





	public void setType_id(int type_id) {
		this.type_id = type_id;
	}





	public String getType_name() {
		return type_name;
	}





	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

}
