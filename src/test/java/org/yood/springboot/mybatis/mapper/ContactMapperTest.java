package org.yood.springboot.mybatis.mapper;

import org.yood.springboot.mybatis.SpringBootMybatisApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = SpringBootMybatisApplication.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class ContactMapperTest {

    @Test
    public void testDeleteByUser() throws Exception {

    }


    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testGetByUser() throws Exception {

    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {

    }
}