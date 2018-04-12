package com.mango.web.dao;

import com.mango.web.pojo.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tobywang on 4/11/2018.
 */
public interface UserDao {
    User findByName(String name);
    List<User> findAll();
    User findOne(Integer id);
    Serializable save(User student);
}
