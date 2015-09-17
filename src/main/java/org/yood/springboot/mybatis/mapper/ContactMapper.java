package org.yood.springboot.mybatis.mapper;


import org.yood.springboot.mybatis.entity.Contact;

import java.sql.SQLException;
import java.util.List;


public interface ContactMapper {

    void deleteByUser(int userId) throws SQLException;

    void update(Contact contact) throws SQLException;

    List<Contact> getByUser(int userId) throws SQLException;

    Contact get(int id) throws SQLException;

    void add(Contact contact) throws SQLException;

    List<Contact> getAll() throws SQLException;

}
