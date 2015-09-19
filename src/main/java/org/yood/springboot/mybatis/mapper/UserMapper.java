package org.yood.springboot.mybatis.mapper;

import org.yood.springboot.mybatis.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserMapper {

    void insert(User user) throws SQLException;

    void update(User user) throws SQLException;

    User selectById(int id) throws SQLException;

    List<User> selectAll() throws SQLException;

    User selectByName(String name)throws SQLException;
}
