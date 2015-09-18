package org.yood.springboot.mybatis.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yood.springboot.mybatis.entity.User;
import org.yood.springboot.mybatis.service.UserService;
import org.yood.springboot.mybatis.web.utils.RequestUtils;

import javax.servlet.http.HttpServletRequest;


@RestController
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = RequestUtils.LOGIN, method = RequestMethod.POST)
    public ResponseEntity<?> login(String username, String password, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(username, password);
        LOGGER.info("User login successfully, user = {}", loginUser);
        return ResponseEntity.ok().build();
    }
}