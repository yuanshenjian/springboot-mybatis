package org.yood.springboot.mybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yood.springboot.mybatis.entity.Contact;
import org.yood.springboot.mybatis.mapper.ContactMapper;
import org.yood.springboot.mybatis.service.ContactService;

import java.sql.SQLException;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactMapper contactMapper;


    @Override
    @Transactional
    public void update(Contact contact) throws SQLException {
        contactMapper.update(contact);
    }

    @Override
    public List<Contact> queryByUserId(int userId) throws SQLException {
        return contactMapper.selectByUserId(userId);
    }

    @Override
    public Contact queryById(int id) throws SQLException {
        return contactMapper.selectById(id);
    }

    @Override
    @Transactional
    public void add(Contact contact) throws SQLException {
        contactMapper.insert(contact);
    }

    @Override
    public List<Contact> queryAll() throws SQLException {
        return contactMapper.selectAll();
    }
}
