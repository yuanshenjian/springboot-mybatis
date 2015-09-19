package org.yood.springboot.mybatis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private String username;
    private String password;
    private Sex sex;
    private List<Authority.Role> roles;
    private List<Contact> contacts = new ArrayList<>();

    public User(String username) {
        this.username = username;
    }

    public enum Sex {
        MALE,
        FEMALE
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
