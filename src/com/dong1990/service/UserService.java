package com.dong1990.service;

import com.dong1990.domain.User;

public interface UserService {
    User getUserByCodePassword(User user);

    void save(User u);
}
