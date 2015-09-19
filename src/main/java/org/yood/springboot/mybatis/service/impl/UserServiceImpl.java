package org.yood.springboot.mybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yood.springboot.mybatis.entity.User;
import org.yood.springboot.mybatis.mapper.UserMapper;
import org.yood.springboot.mybatis.service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


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
    public User get(int id) throws SQLException {
        return userMapper.selectById(id);
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        try {
            user = userMapper.selectByName(username);
        } catch (SQLException e) {
            throw new UsernameNotFoundException("SQLException", e);
        }
        List<GrantedAuthority> roles = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }
}
