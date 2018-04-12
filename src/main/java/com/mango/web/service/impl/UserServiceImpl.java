package com.mango.web.service.impl;

import com.mango.web.dao.UserDao;
import com.mango.web.pojo.User;
import com.mango.web.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tobywang on 4/11/2018.
 */

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    public List<User> getAll() {
        return userDao.findAll();
    }
}
