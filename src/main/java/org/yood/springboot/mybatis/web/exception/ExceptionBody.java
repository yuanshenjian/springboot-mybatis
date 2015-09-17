package org.yood.springboot.mybatis.web.exception;


public class ExceptionBody {

    public static final String KEY_SYSTEM = "_system";

    private final String field;
    private final BusinessExceptionType businessExceptionType;
    private final SystemErrorType systemErrorType;

    public enum BusinessExceptionType {
        EXISTED,
        TOO_LARGE
    }

    public enum SystemErrorType {
        BUSY
    }

    private ExceptionBody(String field, BusinessExceptionType businessErrorType) {
        this.field = field;
        this.businessExceptionType = businessErrorType;
        systemErrorType = null;
    }

    public ExceptionBody(String field, SystemErrorType systemErrorType) {
        this.field = field;
        this.systemErrorType = systemErrorType;
        businessExceptionType = null;
    }

    public static ExceptionBody of(String field, BusinessExceptionType exceptionType) {
        return new ExceptionBody(field, exceptionType);
    }

    public String getField() {
        return field;
    }

    public BusinessExceptionType getBusinessExceptionType() {
        return businessExceptionType;
    }

    public SystemErrorType getSystemErrorType() {
        return systemErrorType;
    }
}
