package org.yood.springboot.mybatis.web.controller;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.yood.springboot.mybatis.BasicMockMvcTest;
import org.yood.springboot.mybatis.entity.User;
import org.yood.springboot.mybatis.service.UserService;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest extends BasicMockMvcTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Override
    public Object injectController() {
        return userController;
    }

    @Test
    public void testGet() throws Exception {
        User user = new User("Shenjian");
        when(userService.getByUserName(anyString())).thenReturn(user);
        mockGet("/users/1", MediaType.APPLICATION_JSON).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name", is("Shenjian")));
    }

    @Test
    public void testGetAll() throws Exception {
        User user1 = new User("ShenjianYuan1");
        User user2 = new User("ShenjianYuan2");
        when(userService.getAll()).thenReturn(Arrays.asList(user1, user2));
        mockGet("/users", MediaType.APPLICATION_JSON).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("ShenjianYuan1")))
                .andExpect(jsonPath("$[1].name", is("ShenjianYuan2")));
    }

    @Test
    public void testAdd() throws Exception {
        User user = new User("Shenjian,Yuan");
        user.setSex(User.Sex.MALE);
        mockPost("/users", MediaType.APPLICATION_JSON, JSON.toJSONString(user)).andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {
        User user = new User("Shenjian,Yuan");
        mockPut("/users", MediaType.APPLICATION_JSON, JSON.toJSONString(user)).andExpect(status().isOk());
    }
}