package org.yood.springboot.mybatis.web.validator;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.yood.springboot.mybatis.entity.Authority;
import org.yood.springboot.mybatis.entity.User;
import org.yood.springboot.mybatis.web.exception.ExceptionCode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserValidatorTest {

    private UserValidator userValidator;
    private Errors errors;

    @Before
    public void setup() {
        userValidator = new UserValidator();
        errors = new BindException(new User(), "user");
    }

    @Test
    public void testSetSupportClass() throws Exception {
        assertEquals(User.class, userValidator.supportClass());
    }

    @Test
    public void testValidateTarget() throws Exception {
        User user = new User();
        user.setName("sjyuan");
        user.setPassword(new BCryptPasswordEncoder().encode("000"));
        user.setAge(25);
        user.setPhone("18192235667");
        user.setSex(User.Sex.MALE);
        List<Authority.Role> roles = new ArrayList<>();
        roles.add(Authority.Role.ADMIN);
        user.setRoles(roles);
        userValidator.validateTarget(user, errors);
        assertFalse("Has errors={" + errors.getAllErrors() + "}", errors.hasErrors());
    }

    @Test
    public void testValidateTargetWhenErrorPhone() throws Exception {
        User user = new User();
        user.setName("sjyuan");
        user.setPassword(new BCryptPasswordEncoder().encode("000"));
        user.setAge(25);
        user.setPhone("181922");
        user.setSex(User.Sex.MALE);
        List<Authority.Role> roles = new ArrayList<>();
        roles.add(Authority.Role.ADMIN);
        user.setRoles(roles);
        userValidator.validateTarget(user, errors);
        assertTrue("No errors!", errors.hasErrors());
        assertEquals(1, errors.getFieldErrorCount());
        assertEquals(ExceptionCode.Validation.PHONE_LENGTH_OUT_OF_RANGE, errors.getFieldError("phone").getCode());
    }
}