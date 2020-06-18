package com.yidong.common;

/**
 *异常处理
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -8713486886851619915L;
	
	public ServiceException() {}
	
	public ServiceException(String msg) {
		super(msg);
	}
	
	public ServiceException(Throwable cause) {
		super(cause);
	}
}
