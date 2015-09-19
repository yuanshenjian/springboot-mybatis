package org.yood.springboot.mybatis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.yood.springboot.mybatis.SpringBootMybatisApplication;
import org.yood.springboot.mybatis.entity.User;
import org.yood.springboot.mybatis.mapper.UserMapper;
import org.yood.springboot.mybatis.service.impl.UserServiceImpl;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = SpringBootMybatisApplication.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService =  new UserServiceImpl();

    @Mock
    private UserMapper userMapper;


    @Test
    public void testAdd() throws Exception {
        User user = new User();
        userService.add(user);
        userService.add(user);
        userService.add(user);
        verify(userMapper, times(3)).insert(user);
    }

    @Test
    public void testUpdate() throws Exception {
        userService.update(null);
        verify(userMapper, times(1)).update(null);
    }

    @Test
    public void testGet() throws Exception {
        User user = new User();
        user.setId(1);
        when(userMapper.selectById(anyInt())).thenReturn(user);
        assertNotNull(userService.get(anyInt()));
        assertEquals(userService.get(anyInt()).getId(), user.getId());
    }

    @Test
    public void testGetAll() throws Exception {
        User user = new User();
        User user1 = new User();
        when(userMapper.selectAll()).thenReturn(Arrays.asList(user, user1));
        assertEquals(userService.getAll().size(), 2);
    }
}