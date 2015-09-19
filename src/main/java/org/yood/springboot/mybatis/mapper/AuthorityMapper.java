package org.yood.springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Select;
import org.yood.springboot.mybatis.entity.Authority;

import java.sql.SQLException;
import java.util.List;

public interface AuthorityMapper {

    @Select("select * from sbm_authority where username=#{1}")
    List<Authority> selectByUserName(String username) throws SQLException;
}
