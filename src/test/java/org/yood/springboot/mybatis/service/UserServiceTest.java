package org.yood.springboot.mybatis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.yood.springboot.mybatis.entity.User;
import org.yood.springboot.mybatis.mapper.UserMapper;
import org.yood.springboot.mybatis.service.impl.UserServiceImpl;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService =  new UserServiceImpl();

    @Mock
    private UserMapper userMapper;

    @Test
    public void testAdd() throws Exception {
        User user = new User("admin");
        user.setPassword("000");
        userService.add(user);
        userService.add(user);
        verify(userMapper, times(2)).insert(user);
    }

    @Test
    public void testUpdate() throws Exception {
        userService.update(null);
        verify(userMapper, times(1)).update(null);
    }

    @Test
    public void testGet() throws Exception {
        User user = new User("");
        when(userMapper.selectByName(anyString())).thenReturn(user);
        assertNotNull(userService.getByUserName(anyString()));
    }

    @Test
    public void testGetAll() throws Exception {
        User user = new User("");
        User user1 = new User("");
        when(userMapper.selectAll()).thenReturn(Arrays.asList(user, user1));
        assertEquals(userService.getAll().size(), 2);
    }
}