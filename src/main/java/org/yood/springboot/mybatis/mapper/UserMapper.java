package org.yood.springboot.mybatis.mapper;

import org.yood.springboot.mybatis.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserMapper {

    void add(User user) throws SQLException;

    void update(User user) throws SQLException;

    User queryById(int id) throws SQLException;

    List<User> queryAll() throws SQLException;

    User queryByName(String name)throws SQLException;
}
