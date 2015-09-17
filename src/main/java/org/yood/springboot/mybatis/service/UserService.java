package org.yood.springboot.mybatis.service;


import org.yood.springboot.mybatis.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    void add(User user) throws SQLException;

    void update(User user) throws SQLException;

    User get(int id) throws SQLException;

    List<User> getAll() throws SQLException;

}
