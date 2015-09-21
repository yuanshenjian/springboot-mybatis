package org.yood.springboot.mybatis.web.exception;


import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandlerControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandlerControllerAdvice.class);

    @ExceptionHandler({SQLException.class, CommunicationsException.class})
    public ResponseEntity<?> handleSQLException(SQLException exception) {
        LOGGER.error("SQLException", exception);
        ExceptionBody errorBody = new ExceptionBody(ExceptionBody.KEY_SYSTEM, ExceptionBody.SystemErrorType.BUSY);
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(errorBody);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnAuthorizedException.class)
    public void handleHackerException() {
        LOGGER.error("Hacker attack,leave it away,we are safe");
    }


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ExceptionBody>> handleBusinessIllegalArgumentException(BusinessException exception) {
        LOGGER.error("BusinessException {}", exception.getExceptionBodies());
        return ResponseEntity.badRequest().body(exception.getExceptionBodies());
    }
}
