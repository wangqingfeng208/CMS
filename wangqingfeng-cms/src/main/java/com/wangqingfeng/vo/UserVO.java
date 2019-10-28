package com.wangqingfeng.vo;

import com.wangqingfeng.bean.User;

/**
 * @作者 王清锋
 * 2019年10月17日
 * 
 */
public class UserVO extends User {

	private static final long serialVersionUID = 3999810645268831736L;

	private String repassword;

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
	
}
