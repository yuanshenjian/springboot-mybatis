package org.yood.springboot.mybatis.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.yood.springboot.mybatis.web.exception.ExceptionCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    @NotBlank
    @Size(min = 3, max = 55, message = ExceptionCode.Validation.NAME_LENGTH_OUT_OF_RANGE)
    private String name;

    @NotBlank
    @Size(min = 3, max = 255, message = ExceptionCode.Validation.PASSWORD_LENGTH_OUT_OF_RANGE)
    private String password;

    @Size(min = 7, max = 15, message = ExceptionCode.Validation.PHONE_LENGTH_OUT_OF_RANGE)
    private String phone;

    @Min(value = 5, message = ExceptionCode.Validation.AGE_TOO_YOUNG)
    @Max(value = 100, message = ExceptionCode.Validation.AGE_TOO_OLD)
    private int age;

    @NotNull
    private Sex sex;

    @NotEmpty
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
}
