package org.yood.springboot.mybatis.service;


import org.yood.springboot.mybatis.entity.Contact;

import java.sql.SQLException;
import java.util.List;


public interface ContactService {

    void update(Contact contact) throws SQLException;

    List<Contact> queryByUserId(int userId) throws SQLException;

    Contact queryById(int id) throws SQLException;

    void add(Contact contact) throws SQLException;

    List<Contact> queryAll() throws SQLException;

}
