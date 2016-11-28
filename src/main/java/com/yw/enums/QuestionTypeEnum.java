package com.yw.enums;

public enum QuestionTypeEnum {
	
	BLK(1,"填空题"),
	MTC(2,"选择题"),
	EXP(3,"实验题"),
	CAL(4,"计算题"),
	ANS(5,"简答题"),
	PIC(6,"作图题"),
	ARG(7,"辨析题"),
	EST(8,"估算题"),
	TFQ(9,"判断题"),
	PRV(10,"证明题");

	private int id;
	private String name;
	
	QuestionTypeEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public static QuestionTypeEnum nameOf(int index){
		for(QuestionTypeEnum state : values()){
			if(state.getId() == index)
				return state;
		}
		return null;
	}
	
	public static QuestionTypeEnum idOf(String name){
		for(QuestionTypeEnum state : values()){
			if(state.getName() == name)
				return state;
		}
		return null;
	}

}
