package com.dong1990.service.impl;

import com.dong1990.dao.UserDao;
import com.dong1990.domain.User;
import com.dong1990.service.UserService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=true)
public class UserServiceImpl implements UserService{


    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserByCodePassword(User user) {
        // 1.根据登录名称查询登录用户
        User u = userDao.getByUserCode(user.getUser_code());
        // 2.pen端用户是否存在，不存在=>抛出异常，提示用户名不存在
        if (u == null) {
            throw new RuntimeException("用户名不存在！");
        }
        // 3.判断用户密码是否正确，不正确=>抛出异常，提示用户密码错误
        if (!u.getUser_password().equals(user.getUser_password())) {
            throw new RuntimeException("密码错误！");
        }
        // 4.返回查询到的用户对象
        return u;
    }

    @Override
    @Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
    public void save(User u) {
        User user = userDao.getByUserCode(u.getUser_code());
        if (user != null) {
            throw new RuntimeException("用户名已存在！");
        }
        u.setUser_state('1');
        userDao.save(u);
    }
}
