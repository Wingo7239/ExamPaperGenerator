package com.yw.service;

public interface PaperService {

	public String generatePaper(int id);
	
	
	int insertPaper(String uid,String paper);
	
	boolean deletePaper(int id);
}
