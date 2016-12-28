package com.yw.dto;

import com.yw.domain.Question;

public class QuesPreview {
	private Integer id;
	private String memo;
	private int type;
	
	
	
	
	public QuesPreview(Integer id, String memo, int type) {
		this.id = id;
		this.memo = memo;
		this.type = type;
	}
	
	
	public QuesPreview(Question q) {
		this.id = q.getId();
		this.memo = q.getMemo();
		this.type = q.getType();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

}
