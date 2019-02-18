package com.finance.base.exception;

import java.io.Serializable;

/**
 * Created by wujunjie on 2017-06-21.
 */
public class BusinessException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 4479842101010527605L;

    public BusinessException(String code) {
        this.code = code;
    }

    public BusinessException(String code, String extMsg) {
        this.code = code;
        this.extMsg = extMsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;

    //附加信息，用户替换
    private String extMsg;

    public String getExtMsg() {
        return extMsg;
    }

    public void setExtMsg(String extMsg) {
        this.extMsg = extMsg;
    }
}
