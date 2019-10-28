package com.wangqingfeng.exception;

/**
 * @作者 王清锋
 * 2019年10月17日
 * 
 */
public class CMSException extends RuntimeException {

	private static final long serialVersionUID = -6329452908815574557L;
	
	public CMSException() {
		
	}
	
	public CMSException(String message) {
		super(message);
	}
}
