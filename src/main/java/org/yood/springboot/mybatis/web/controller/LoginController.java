package org.yood.springboot.mybatis.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yood.springboot.mybatis.entity.User;
import org.yood.springboot.mybatis.service.UserService;
import org.yood.springboot.mybatis.web.util.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;


@Controller
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

    @RequestMapping("/")
    public String home(Map<String, Object> model) {
        model.put("message", "Hello World");
        model.put("title", "Hello Home");
        model.put("date", new Date());
        return "home";
    }
}