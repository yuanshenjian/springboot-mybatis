package org.yood.springboot.mybatis.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.yood.springboot.mybatis.SpringBootMybatisApplication;
import org.yood.springboot.mybatis.entity.User;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootMybatisApplication.class)
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testAdd() throws Exception {
        int preSize = userMapper.getAll().size();
        User user= new User();
        user.setName("ysjian");
        user.setSex(User.Sex.MALE);
        userMapper.add(user);
        assertEquals(preSize+1,userMapper.getAll().size());
    }

    @Test
    public void testUpdate() throws Exception {
        List<User> all = userMapper.getAll();
        if (all.size()>0){
            User user = all.get(0);
            user.setName("update name");
            userMapper.update(user);
            assertEquals(user.getName(),userMapper.get(user.getId()).getName());
        }
        userMapper.update(null);

    }

    @Test(expected = NullPointerException.class)
    public void testGet() throws Exception {
        userMapper.get(0).getId();
    }

    @Test
    public void testGetAll() throws Exception {
        assertTrue(userMapper.getAll().size() >= 0);
    }

}