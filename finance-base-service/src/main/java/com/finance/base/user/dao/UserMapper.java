package com.finance.base.user.dao;

import com.finance.common.beans.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by liuyw on 19/2/15.
 */
@Mapper
public interface UserMapper {

    List<User> getUsers(Integer id);

}
