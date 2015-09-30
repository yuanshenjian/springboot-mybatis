package org.yood.springboot.mybatis.web.exception;


public class ExceptionBody {

    private final String field;
    private final ExceptionCode exceptionCode;

    public enum ExceptionCode {
        EXISTED,
        TOO_LARGE
    }

    private ExceptionBody(String field, ExceptionCode exceptionCode) {
        this.field = field;
        this.exceptionCode = exceptionCode;
    }


    public static ExceptionBody of(String field, ExceptionCode exceptionCode) {
        return new ExceptionBody(field, exceptionCode);
    }

    public String getField() {
        return field;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }

}
