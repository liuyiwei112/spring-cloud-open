package com.finance.acct.base;

import com.finance.common.beans.Result;
import com.finance.common.constants.ServiceConstant;
import com.finance.acct.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;

public abstract class BaseController {

	public  Logger logger =LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("messageSource")
	public MessageSource messageSource;
	@Autowired
	@Qualifier("localeResolver")
	public LocaleResolver localeResolver;
	@Autowired
	RestTemplate restTemplate;
	
	public String getMessage(String key){
		return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
	}

	/**
	 * 检查用户登录
	 * @param token 登录token
	 * @param appid 登录端识别
	 * @return 如果校验失败,则返回失败的结果; 成功则返回用户信息
	 */
	public Result checkUserAuth(String token, String appid) throws BusinessException {
		String url= ServiceConstant.BASE+"/user/current/info?token="+token+"&appid="+appid;
		Result data=restTemplate.getForObject(url,Result.class);
		//校验登录
		if(data==null||!data.isSuccess()){
			throw new BusinessException("User.Info.NotLogin");
		}
		return data;
	}
	
 

}

