package org.yood.springboot.mybatis.service.impl;

import org.yood.springboot.mybatis.entity.User;
import org.yood.springboot.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yood.springboot.mybatis.service.UserService;

import java.sql.SQLException;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void add(User user) throws SQLException {
        userMapper.add(user);
    }

    @Override
    @Transactional
    public void update(User user) throws SQLException {
            userMapper.update(user);
    }

    @Override
    public User get(int id) throws SQLException {
        return userMapper.get(id);
    }

    @Override
    public List<User> getAll() throws SQLException {
        return userMapper.getAll();
    }
}
