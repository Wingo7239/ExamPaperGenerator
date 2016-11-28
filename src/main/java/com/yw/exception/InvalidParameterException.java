package com.yw.exception;

/**
 * 参数错误异常
 * @author YingguoWu
 *
 */

public class InvalidParameterException extends RuntimeException{

	public InvalidParameterException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidParameterException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
