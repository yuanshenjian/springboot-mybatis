package org.yood.springboot.mybatis.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yood.springboot.mybatis.entity.User;
import org.yood.springboot.mybatis.mapper.AuthorityMapper;
import org.yood.springboot.mybatis.mapper.UserMapper;
import org.yood.springboot.mybatis.service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public void add(User user) throws SQLException {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userMapper.insert(user);
    }

    @Override
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("username = {}", username);
        try {
            User user = userMapper.selectByName(username);
            List<GrantedAuthority> roles = authorityMapper.selectByUserName(username)
                    .stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getRole().name()))
                    .collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), roles);
        } catch (SQLException e) {
            throw new UsernameNotFoundException("SQLException", e);
        }
    }
}
