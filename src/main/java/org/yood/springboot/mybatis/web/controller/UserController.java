package org.yood.springboot.mybatis.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.yood.springboot.mybatis.entity.User;
import org.yood.springboot.mybatis.service.UserService;
import org.yood.springboot.mybatis.web.exception.UnAuthorizedException;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "users/{username}",
                    method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable String username) throws UnAuthorizedException, SQLException {
        User user = userService.getByUserName(username);
        if (null == user) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "users",
                    method = RequestMethod.GET)
    public List<User> getAll() throws IOException, SQLException {
        return userService.getAll();
    }

    @RequestMapping(value = "users",
                    method = RequestMethod.PUT)
    public ResponseEntity<?> update(@Valid @RequestBody User user,
                                    BindingResult bindingResult) throws UnAuthorizedException, SQLException {
        userService.update(user);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "users",
                    method = RequestMethod.POST)
    public ResponseEntity<?> add(@Valid @RequestBody User user,BindingResult bindingResult) throws SQLException, UnAuthorizedException {
        if (StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getSex())) {
            throw UnAuthorizedException.newInstance();
        }
        userService.add(user);
        return ResponseEntity.ok().build();
    }
}