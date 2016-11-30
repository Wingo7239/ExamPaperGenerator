package com.yw.dto;

import java.util.ArrayList;

import com.yw.domain.Question;


public class Cart {
	
	
	private String name;
	private int count;
	private ArrayList<Question> quesList;
	
	public Cart(String name, int count, ArrayList<Question> quesList) {		
		this.name = name;
		this.setCount(count);
		this.quesList = quesList;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Question> getQuesList() {
		return quesList;
	}
	public void setQuesList(ArrayList<Question> quesList) {
		this.quesList = quesList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
}
