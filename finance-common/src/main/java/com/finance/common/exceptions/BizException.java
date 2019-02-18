package com.finance.common.exceptions;

public class BizException extends RuntimeException{

	private static final long serialVersionUID = -2727676368173586878L;

	private String code;

	public BizException(String code) {
		this.code = code;
	}

	public BizException(String code,String message ) {
		super(message);
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	
	
	
}
