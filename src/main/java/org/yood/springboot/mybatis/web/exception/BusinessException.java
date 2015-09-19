package org.yood.springboot.mybatis.web.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BusinessException extends Exception {

    private BusinessException() {
    }

    private final List<ExceptionBody> EXCEPTION_BODIES = new ArrayList<>();


    public static BusinessException fromException(ExceptionBody... exceptionBodies) {
        BusinessException exception = new BusinessException();
        Collections.addAll(exception.getExceptionBodies(), exceptionBodies);
        return exception;
    }

    public List<ExceptionBody> getExceptionBodies() {
        return EXCEPTION_BODIES;
    }
}
