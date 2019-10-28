package com.wangqingfeng.utils;

/**
 * @作者 王清锋
 * 2019年10月24日
 * 
 */
public enum ArticleEnum {
	
	HTML(0,"html"),IMAGE(1,"image");
	
	private Integer code;
	private String name;
	
	ArticleEnum(Integer code, String name){
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
