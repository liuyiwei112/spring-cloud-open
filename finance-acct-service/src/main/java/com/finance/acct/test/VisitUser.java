package com.finance.acct.test;

import com.finance.common.beans.Result;
import com.finance.common.constants.ServiceConstant;
import com.finance.acct.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by liuyw on 19/2/18.
 */
@RestController
public class VisitUser {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/visitUser")
    public Result doVisitUser(@RequestParam Integer id){
        String url= ServiceConstant.BASE+"/users/?id="+id;
        Result data=restTemplate.getForObject(url,Result.class);
        //校验登录
        if(data==null||!data.isSuccess()){
            throw new BusinessException("User.Info.NotLogin");
        }
        return data;
    }
}
