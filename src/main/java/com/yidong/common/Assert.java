package com.yidong.common;


/**
 *数据的校验
 */
public class Assert {
	
	public static void isNull(Object obj,String msg) {
		if (obj == null) 
			throw new IllegalArgumentException(msg);
	}
	
	public static void isEmpty(Object obj,String msg) {
		if (obj == null || "".equals(obj)) 
			throw new IllegalArgumentException(msg);
	}
	
	/**
	 * 非法参数的校验
	 * @param statement
	 * @param msg
	 */
	public static void isArgumentValid(boolean statement, String msg) {
		if(statement)
			throw new IllegalArgumentException(msg);
	}
	
	/**
	 * 服务异常的处理
	 * @param statement
	 * @param msg
	 */
	public static void isService(boolean statement,String msg) {
		if(statement)
			throw new ServiceException(msg);
	}
	
}
