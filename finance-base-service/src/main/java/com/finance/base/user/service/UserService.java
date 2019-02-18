package com.finance.base.user.service;

import com.alibaba.fastjson.JSONObject;
import com.finance.base.user.dao.UserMapper;
import com.finance.common.beans.User;
import com.finance.common.utils.RedisCommons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuyw on 19/2/15.
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisCommons redisCommons;

    public List<User> getUsers(Integer id){
        List<User> users = userMapper.getUsers(id);
        if(users.size()>0){
            String data = JSONObject.toJSONString(users.get(0));
            System.out.println(data);
            redisCommons.set("user-"+id,data);
        }
        System.out.println(redisCommons.get("user-"+id,String.class));
        return users;
    }
}
