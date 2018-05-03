package com.dong1990.web.action;

import com.dong1990.domain.User;
import com.dong1990.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;


@Scope("prototype")
@Controller("userAction")
public class UserAction extends ActionSupport implements ModelDriven<User>{

    private User user = new User();
    @Resource(name = "userService")
    private UserService userService;

    // 整合方案1：不推荐这样的方式
    // 不推荐理由:最好由spring完整管理action的生命周期.spring中功能才应用到Action上.
    // 这里的名字要和applicationContext.xml里面的配置名字一样才能实现自动装配
    // <bean name="userService" class="com.dong1990.service.impl.UserServiceImpl"></bean>
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String login(){
        // 1.调用Service执行登录逻辑
        User u = userService.getUserByCodePassword(user);
        // 2.将返回的user对象放入session域
        ActionContext.getContext().getSession().put("user",u);
        // 3.重定向到项目首页
        return "toHome";
    }

    public String regist(){
        try {
            userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            ActionContext.getContext().put("error",e.getMessage());
            return "regist";
        }

        /*ActionContext.getContext().put("succ","注册成功，跳转登录页面");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return "toLogin";
    }

    @Override
    public User getModel() {
        return user;
    }
}
