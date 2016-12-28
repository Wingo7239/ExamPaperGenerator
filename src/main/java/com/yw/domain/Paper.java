package com.yw.domain;
// Generated Dec 13, 2016 4:54:28 PM by Hibernate Tools 3.4.0.CR1

/**
 * Paper generated by hbm2java
 */
public class Paper implements java.io.Serializable {

	private Integer id;
	private String uid;
	private String paper;

	public Paper() {
	}
	
	public Paper(String paper) {
		this.paper = paper;
	}
	
	public Paper(int id) {
		this.id = id;
	}

	public Paper(String uid, String paper) {
		this.uid = uid;
		this.paper = paper;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPaper() {
		return this.paper;
	}

	public void setPaper(String paper) {
		this.paper = paper;
	}

}
