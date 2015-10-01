package org.yood.springboot.mybatis.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.yood.springboot.mybatis.entity.User;
import org.yood.springboot.mybatis.web.exception.ExceptionCode;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (StringUtils.isEmpty(user.getName()) || !user.getName().matches("[\\w]{3,55}")) {
            errors.rejectValue("name", ExceptionCode.Validation.NAME_LENGTH_OUT_OF_RANGE);
        }
        if (null != user.getPhone() && !user.getPhone().matches("\\d{7,15}")) {
            errors.rejectValue("phone", ExceptionCode.Validation.PHONE_LENGTH_OUT_OF_RANGE);
        }
        if (!user.getPassword().matches(".{3,255}")) {
            errors.rejectValue("password", ExceptionCode.Validation.PASSWORD_LENGTH_OUT_OF_RANGE);
        }
        if (user.getAge() < 3) {
            errors.rejectValue("age", ExceptionCode.Validation.AGE_TOO_YOUNG);
        }
        if (user.getAge() > 100) {
            errors.rejectValue("age", ExceptionCode.Validation.AGE_TOO_OLD);
        }
    }
}
