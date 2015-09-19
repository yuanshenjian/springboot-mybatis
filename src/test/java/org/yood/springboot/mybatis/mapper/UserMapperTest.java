package org.yood.springboot.mybatis.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testAdd() throws Exception {
        int preSize = userMapper.selectAll().size();
        User user = new User();
        user.setName("ysjian");
        user.setPassword(new BCryptPasswordEncoder().encode("000"));
        user.setSex(User.Sex.MALE);
        userMapper.insert(user);
        assertEquals(preSize + 1, userMapper.selectAll().size());
    }

    @Test
    public void testUpdate() throws Exception {
        List<User> all = userMapper.selectAll();
        if (all.size() > 0) {
            User user = all.get(0);
            user.setSex(User.Sex.FEMALE);
            userMapper.update(user);
            assertEquals(user.getSex(), userMapper.selectByName(user.getName()).getSex());
        }
        userMapper.update(null);
    }

    @Test(expected = NullPointerException.class)
    public void testGet() throws Exception {
        userMapper.selectByName("").getName();
    }

    @Test
    public void testGetAll() throws Exception {
        assertTrue(userMapper.selectAll().size() >= 0);
    }

}