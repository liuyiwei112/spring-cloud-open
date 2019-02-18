package com.finance.base.user.controller;

import com.finance.base.user.service.UserService;
import com.finance.common.beans.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuyw on 19/2/15.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public Result getUserList(@RequestParam Integer id){
        return Result.ok(userService.getUsers(id));
    }

}
