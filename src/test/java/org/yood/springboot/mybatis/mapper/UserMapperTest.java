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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootMybatisApplication.class)
@Transactional
@TransactionConfiguration
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testAdd() throws Exception {
        int preSize = userMapper.selectAll().size();
        User user = new User();
        user.setName("ysjian003");
        user.setPassword(new BCryptPasswordEncoder().encode("000"));
        user.setSex(User.Sex.MALE);
        userMapper.insert(user);
        assertEquals(preSize + 1, userMapper.selectAll().size());
    }

    @Test(expected = NullPointerException.class)
    public void testGet() throws Exception {
        userMapper.selectByName("").getName();
    }
}