package org.yood.springboot.mybatis.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.yood.springboot.mybatis.entity.User;
import org.yood.springboot.mybatis.service.UserService;
import org.yood.springboot.mybatis.web.exception.BusinessException;
import org.yood.springboot.mybatis.web.exception.ExceptionBody;
import org.yood.springboot.mybatis.web.exception.UnAuthorizedException;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


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
                    method = RequestMethod.POST)
    public ResponseEntity<?> add(@Valid @RequestBody User user,
                                 BindingResult bindingResult) throws SQLException, UnAuthorizedException,
                                                                     BusinessException {
        processValidateResult(bindingResult);
        userService.add(user);
        return ResponseEntity.ok().build();
    }

    public void processValidateResult(BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            List<ExceptionBody> exceptionBodies = bindingResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> ExceptionBody.of(fieldError.getField(), fieldError.getDefaultMessage()))
                    .collect(Collectors.toList());
            throw BusinessException.fromException(exceptionBodies);
        }
    }
}