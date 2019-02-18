package com.finance.common.beans;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.Serializable;

@JsonPropertyOrder({"success", "code", "message", "data"})
public class Result implements Serializable {

    private static final long serialVersionUID = 776385756965803733L;

    private static final nullData NULL_DATA = new nullData();

    //是否成功
    private boolean success;

    //错误编码
    private String code;

    //错误信息
    private String message;

    //返回数据
    private Object data = NULL_DATA;

    public Result() {
    }

    public Result(boolean success, String code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        if (data != null) {
            this.data = data;
        }
    }

    public Result(boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public Result(boolean success, String code) {
        this.success = success;
        this.code = code;
    }

    public static Result success(Object data) {
        return new Result(true, "Sys.Request.Success", messageSource.getMessage("Sys.Request.Success", null, LocaleContextHolder.getLocale()), data);
    }

    public static Result success(String code) {
        return new Result(true, code, messageSource.getMessage(code, null, LocaleContextHolder.getLocale()), NULL_DATA);
    }

    public static Result error(String code) {
        return new Result(false, code, messageSource.getMessage(code, null, LocaleContextHolder.getLocale()), NULL_DATA);
    }

    public static Result openapiError(String code) {
        return new Result(false, null, messageSource.getMessage(code, null, LocaleContextHolder.getLocale()), NULL_DATA);
    }

    public static Result success() {
        return Result.success("Sys.Request.Success");
    }

    public static Result error() {
        return Result.success("Sys.Request.Fail");
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    public static Result ok(Object data) {
        return new Result(true, "200", messageSource.getMessage("200", null, LocaleContextHolder.getLocale()), data);

    }

    public static Result ok() {
        return new Result(true, "200", messageSource.getMessage("200", null, LocaleContextHolder.getLocale()), NULL_DATA);
    }

    public static Result ok(String code, String message, Object data) {
        return new Result(true, code, message, data);
    }

    public static Result error(String code, String message, Object data) {
        return new Result(false, code, message, data);
    }

    public static void setMessageSource(MessageSource messageSource) {
        Result.messageSource = messageSource;
    }

    @Autowired
    private static MessageSource messageSource;

    private static class nullData implements Serializable {

        private static final long serialVersionUID = 7220537788461583300L;
    }
}
