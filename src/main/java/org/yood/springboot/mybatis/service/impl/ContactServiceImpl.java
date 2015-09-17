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
    public void deleteByUser(int userId) throws SQLException {
        contactMapper.deleteByUser(userId);
    }


    @Override
    @Transactional
    public void update(Contact contact) throws SQLException {
        contactMapper.update(contact);
    }

    @Override
    public List<Contact> getByUser(int userId) throws SQLException {
        return contactMapper.getByUser(userId);
    }

    @Override
    public Contact get(int id) throws SQLException {
        return contactMapper.get(id);
    }

    @Override
    @Transactional
    public void add(Contact contact) throws SQLException {
        contactMapper.add(contact);
    }

    @Override
    public List<Contact> getAll() throws SQLException {
        return contactMapper.getAll();
    }
}
