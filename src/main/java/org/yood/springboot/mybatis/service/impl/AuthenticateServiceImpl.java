package org.yood.springboot.mybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.yood.springboot.mybatis.entity.User;
import org.yood.springboot.mybatis.mapper.UserMapper;
import org.yood.springboot.mybatis.service.AuthenticateService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            User user = userMapper.selectByName(username);
            List<GrantedAuthority> roles = user.getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(role.name()))
                    .collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                                                                          roles);
        } catch (SQLException e) {
            throw new UsernameNotFoundException("SQLException", e);
        }
    }
}
