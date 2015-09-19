package org.yood.springboot.mybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yood.springboot.mybatis.entity.User;
import org.yood.springboot.mybatis.mapper.UserMapper;
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
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userMapper.insert(user);
    }

    @Override
    @Transactional
    public void update(User user) throws SQLException {
        userMapper.update(user);
    }


    @Override
    public List<User> getAll() throws SQLException {
        return userMapper.selectAll();
    }

    @Override
    public User getLoginUser(String username, String password) {
        User loginUser = new User(username);
        return loginUser;
    }

    @Override
    public User getByUserName(String username) throws SQLException {
        return userMapper.selectByName(username);
    }

}
