package org.yood.springboot.mybatis.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.yood.springboot.mybatis.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService extends UserDetailsService{

    void add(User user) throws SQLException;

    void update(User user) throws SQLException;

    List<User> getAll() throws SQLException;

    User getLoginUser(String username, String password);

    User getByUserName(String username) throws SQLException;
}
