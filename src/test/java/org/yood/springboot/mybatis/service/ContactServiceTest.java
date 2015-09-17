package org.yood.springboot.mybatis.service;

import org.yood.springboot.mybatis.service.impl.ContactServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.yood.springboot.mybatis.SpringBootMybatisApplication;
import org.yood.springboot.mybatis.mapper.ContactMapper;

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = SpringBootMybatisApplication.class)
public class ContactServiceTest {

    @InjectMocks
    private ContactService contactService = new ContactServiceImpl();

    @Mock
    private ContactMapper contactMapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteByUser() throws Exception {
    }

    @Test
    public void testUpdate() throws Exception {
    }

    @Test
    public void testAdd() throws Exception {
    }


    @Test
    public void testGetAll() throws Exception {

    }

    @Test
    public void testGetByUser() throws Exception {

    }

    @Test
    public void testGet() throws Exception {

    }


}