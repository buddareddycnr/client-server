package com.openmedical.server.util;

import com.openmedical.server.exceptions.UnknownException;
import com.openmedical.server.exceptions.UserNotFoundException;
import com.openmedical.server.exceptions.UserSaveOrUpdateOperationFailedException;
import com.openmedical.server.exceptions.InvalidEmailIdPatternException;
import com.openmedical.server.exceptions.InvalidMobileNumberFormatException;
import com.openmedical.server.exceptions.MandatoryFieldMissingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ClientServerControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundException(UserNotFoundException userNotFoundException, WebRequest webRequest){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", userNotFoundException.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> sqlException(SQLException sqlException, WebRequest webRequest){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", sqlException.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UserSaveOrUpdateOperationFailedException.class)
    public ResponseEntity<Object> userSaveOrUpdateOperationFailedException(UserSaveOrUpdateOperationFailedException userSaveOrUpdateOperationFailedException, WebRequest webRequest){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", userSaveOrUpdateOperationFailedException.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(MandatoryFieldMissingException.class)
    public ResponseEntity<Object> mandatoryFieldMissingException(MandatoryFieldMissingException mandatoryFieldMissingException, WebRequest webRequest){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", mandatoryFieldMissingException.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidEmailIdPatternException.class)
    public ResponseEntity<Object> invalidEmailIdPatternException(InvalidEmailIdPatternException invalidEmailIdPatternException, WebRequest webRequest){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", invalidEmailIdPatternException.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidMobileNumberFormatException.class)
    public ResponseEntity<Object> invalidMobileNumberFormatException(InvalidMobileNumberFormatException invalidMobileNumberFormatException, WebRequest webRequest){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", invalidMobileNumberFormatException.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnknownException.class)
    public ResponseEntity<Object> unknownException(UnknownException unknownException, WebRequest webRequest){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", unknownException.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
