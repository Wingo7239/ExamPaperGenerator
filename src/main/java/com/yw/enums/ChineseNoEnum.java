package com.yw.enums;

public enum ChineseNoEnum {
	ONE("一、"), TWO("二、"), THREE("三、"), FOUR("四、"), FIVE("五、"), SIX("六、"), SEVEN("七、"), EIGHT("八、"), NINE("九、");
	
	private String name;
	ChineseNoEnum(String name){
		this.name = name;
	}
	
	public static String getName(int i){
		for(ChineseNoEnum state : ChineseNoEnum.values()){
			if(state.ordinal() == i-1)
				return state.name;
		}
		return "";		
	}
}
