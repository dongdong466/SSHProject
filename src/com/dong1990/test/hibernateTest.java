package com.dong1990.test;

import com.dong1990.dao.UserDao;
import com.dong1990.domain.User;
import com.dong1990.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

// 测试hibernate框架
// 下边是整合hibernate和Spring测试的注解
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class hibernateTest {

    @Resource(name="userService")
    private UserService userService;

    @Test
    public void fun4() {
        User u = new User();

        u.setUser_code("xihaha");
        u.setUser_name("嘻哈哈1");
        u.setUser_password("1234");
        u.setUser_state('1');

        userService.save(u);
    }

    @Resource(name="userDao")
    private UserDao userDao;

    @Test
    // 测试dao hibernate模板
    public void fun3(){

        User user = userDao.getByUserCode("dongdong");
        System.out.println(user);
    }

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    @Test
    // Spring测试sessionFactory管理
    public void fun2(){
        // 加载配置 是整合hibernate和Spring测试
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        // ----------------------------------------------

        User user = new User();
        user.setUser_code("huihui");
        user.setUser_name("慧慧");
        user.setUser_password("552200");
        user.setUser_state('1');

        session.save(user);


        // ----------------------------------------------
        transaction.commit();
        session.close();
    }

    @Test
    // 单独测试hibernate
    public void fun1(){
        // 加载配置
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        // ----------------------------------------------

        User user = new User();
        user.setUser_code("tom");
        user.setUser_name("汤姆");
        user.setUser_password("123456");
        user.setUser_state('1');

        session.save(user);


        // ----------------------------------------------
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
