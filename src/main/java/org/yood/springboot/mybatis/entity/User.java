package org.yood.springboot.mybatis.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    @NotNull
    @Size(min = 3, max = 55)
    private String name;

    @NotNull
    @Size(min = 3, max = 30, message = "validation.password.length_error")
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
