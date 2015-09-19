package org.yood.springboot.mybatis.mapper;


import org.yood.springboot.mybatis.entity.Contact;

import java.sql.SQLException;
import java.util.List;


public interface ContactMapper {

    void update(Contact contact) throws SQLException;

    List<Contact> selectByUserId(int userId) throws SQLException;

    Contact selectById(int id) throws SQLException;

    void insert(Contact contact) throws SQLException;

    List<Contact> selectAll() throws SQLException;

}
