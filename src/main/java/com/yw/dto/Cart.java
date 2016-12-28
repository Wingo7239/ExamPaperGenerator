package com.yw.dto;

import java.util.ArrayList;
import java.util.List;


public class Cart {
	
	
	private String name;
	private int count;
	private ArrayList<String> queslist;
	
	public Cart(String name, int count, List<String> quesList) {		
		this.name = name;
		this.setCount(count);
		this.queslist = (ArrayList<String>) quesList;
	}
	
	public Cart(String name, int count, String[] quesList) {		
		this.name = name;
		this.setCount(count);
		this.queslist = new ArrayList<String>();
		for(String s:queslist){
			this.queslist.add(s);
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getQueslist() {
		return queslist;
	}
	public void setQueslist(ArrayList<String> quesList) {
		this.queslist = quesList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
}
