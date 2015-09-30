package org.yood.springboot.mybatis.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    @Size(min = 3, max = 55)
    private String name;

    @Size(min = 3, max = 30)
    private String password;

    @Size(min = 7, max = 15)
    private String phone;

    @Min(5)
    @Max(100)
    private int age;

    private Sex sex;
    private List<Authority.Role> roles;


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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Authority.Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Authority.Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", roles=" + roles +
                '}';
    }
}
