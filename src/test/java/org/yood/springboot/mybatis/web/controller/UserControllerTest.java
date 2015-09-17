package org.yood.springboot.mybatis.web.controller;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.yood.springboot.mybatis.entity.User;
import org.yood.springboot.mybatis.service.UserService;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;


    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = standaloneSetup(userController).build();
    }


    @Test
    public void testGet() throws Exception {
        User user = new User("Shenjian");
        user.setId(1);
        when(userService.get(anyInt())).thenReturn(user);
        mockMvc.perform(get("/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name", is("Shenjian")));
    }

    @Test
    public void testGetAll() throws Exception {
        User user1 = new User("ShenjianYuan1");
        User user2 = new User("ShenjianYuan2");
        when(userService.getAll()).thenReturn(Arrays.asList(user1, user2));
        mockMvc.perform(get("/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("ShenjianYuan1")))
                .andExpect(jsonPath("$[1].name", is("ShenjianYuan2")));
    }

    @Test
    public void testAdd() throws Exception {
        User user = new User("Shenjian,Yuan");
        user.setSex(User.Sex.MALE);
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(user)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {
        User user = new User("Shenjian,Yuan");
        user.setId(1);
        mockMvc.perform(put("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(user)))
                .andExpect(status().isOk());
    }
}