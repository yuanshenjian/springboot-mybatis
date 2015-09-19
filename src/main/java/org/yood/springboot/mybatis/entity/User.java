package org.yood.springboot.mybatis.entity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private String name;
    private String password;
    private Sex sex;
    private List<Authority.Role> roles;

    public User() {
    }

    public User(String username) {
        this.name = username;
    }

    public enum Sex {
        MALE,
        FEMALE
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public List<Authority.Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Authority.Role> roles) {
        this.roles = roles;
    }

}
