package com.dong1990.dao;

import com.dong1990.domain.User;

public interface UserDao extends BaseDao<User>{
    User getByUserCode(String usercode);
}
