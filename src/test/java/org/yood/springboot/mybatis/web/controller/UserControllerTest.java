package org.yood.springboot.mybatis.web.controller;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.yood.springboot.mybatis.BasicMockMvcTest;
import org.yood.springboot.mybatis.entity.Authority;
import org.yood.springboot.mybatis.entity.User;
import org.yood.springboot.mybatis.service.UserService;
import org.yood.springboot.mybatis.util.JSONUtils;
import org.yood.springboot.mybatis.web.validator.UserValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserControllerTest extends BasicMockMvcTest {

    @Mock
    private UserService userService;

    @Mock
    private UserValidator userValidator;

    @InjectMocks
    private UserController userController;

    @Override
    public Object injectController() {
        return userController;
    }

    @Test
    public void testGet() throws Exception {
        User user = new User();
        user.setName("sjyuan");
        user.setAge(25);
        user.setSex(User.Sex.FEMALE);
        when(userService.getByUserName(anyString())).thenReturn(user);
        mockGet("/users/" + user.getName(), MediaType.APPLICATION_JSON).andExpect(status().isOk())
                .andExpect(jsonPath("$.age", is(25)))
                .andExpect(jsonPath("$.sex", is(User.Sex.FEMALE.name())))
                .andExpect(jsonPath("$.name", is("sjyuan")));
        verify(userService, times(1)).getByUserName(user.getName());
    }

    @Test
    public void testGetAll() throws Exception {
        User user1 = new User();
        user1.setName("sjyuan1");
        User user2 = new User();
        user2.setName("sjyuan2");
        when(userService.getAll()).thenReturn(Arrays.asList(user1, user2));
        mockGet("/users", MediaType.APPLICATION_JSON).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("sjyuan1")))
                .andExpect(jsonPath("$[1].name", is("sjyuan2")));
        verify(userService, times(1)).getAll();
    }

    @Test
    public void testAddSuccessfully() throws Exception {
        User user = new User();
        user.setName("sjyuan");
        user.setPassword(new BCryptPasswordEncoder().encode("000"));
        user.setAge(25);
        user.setPhone("18192235667");
        user.setSex(User.Sex.MALE);
        List<Authority.Role> roles = new ArrayList<>();
        roles.add(Authority.Role.ADMIN);
        user.setRoles(roles);
        doCallRealMethod().when(userValidator).validate(any(User.class), any(Errors.class));
        MockHttpSession session = new MockHttpSession();
        mockPost("/users", MediaType.APPLICATION_JSON, JSONUtils.toJSONString(user), session).andExpect(status().isOk
                ());
        verify(userService).add(any(User.class));
    }
}