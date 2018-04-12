package com.mango.web.dao.impl;

import com.mango.web.dao.UserDao;
import com.mango.web.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by tobywang on 4/11/2018.
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao{
    @Resource
    private SessionFactory sessionFactory;
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
    public User findByName(String name) {
        List<User> users = currentSession().createCriteria(User.class).add(Restrictions.eq("name", name)).list();
        if (!users.isEmpty()) {
            return users.get(0);
        }else {
            return null;
        }
    }
    
    public List<User> findAll() {
        //return currentSession().createQuery("from User").list();
        return (List<User>) currentSession().createCriteria(User.class).list();
    }
    
    public User findOne(Integer id) {
        return (User) currentSession().get(User.class, id);
    }
    
    public Serializable save(User user) {
        return currentSession().save(user);
    }
}
