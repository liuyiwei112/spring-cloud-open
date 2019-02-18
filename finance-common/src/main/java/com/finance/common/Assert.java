package com.finance.common;

import com.finance.common.exceptions.BizException;

import java.util.Collection;


public abstract class Assert {
	 
	/**
	 * @Description: 是否为 true
	 * @param expression
	 * @param messageCode
	 */
	public static void isTrue(boolean expression, String messageCode) {
		if (!expression) {
			throw new BizException(messageCode);
		}
	}
    /**
     * @Description: 对象为空
     * @param object
     * @param messageCode
     */
	public static void isNull(Object object, String messageCode) {
		if (object != null) {
			throw new BizException(messageCode);
		}
	}
    /**
     * @Description: 对象非空
     * @param object
     * @param messageCode
     */
	public static void notNull(Object object, String messageCode) {
		if (object == null) {
			throw new BizException(messageCode);
		}
	}
    /**
     * @Description: 数组非空
     * @param array
     * @param messageCode
     */
	public static void notEmpty(Object[] array, String messageCode) {
		if (array == null || array.length == 0) {
			throw new BizException(messageCode);
		}
	}
	/**
	 * @Description: 集合非空
	 * @param collection
	 * @param messageCode
	 */
	public static void notEmpty(Collection<?> collection, String messageCode) {
		if (collection == null || collection.size() == 0) {
			throw new BizException(messageCode);
		}
	}
	
	/**
	 * @Description: 字符串非空
	 * @param text
	 * @param messageCode
	 */
	public static void hasLength(String text, String messageCode) {
		if (text==null||text.trim().length()==0) {
			throw new BizException(messageCode);
		}
	}
 
	/**
	 * @Description: 字符串相同
	 * @param expected
	 * @param actuals
	 */ 
	public static void equals(String expected,String actuals, String messageCode) {
		if(expected==null||actuals==null||!expected.equals(actuals)){
			throw new BizException(messageCode);
		}
	}
	
	/**
	 * @Description: 字符串不同
	 * @param expected
	 * @param actuals
	 */ 
	public static void notEquals(String expected,String actuals, String messageCode) {
		if(expected==null||actuals==null||expected.equals(actuals)){
			throw new BizException(messageCode);
		}
		 
	}
	
}
