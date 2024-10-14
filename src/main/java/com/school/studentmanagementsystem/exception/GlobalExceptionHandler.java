package com.school.studentmanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(StudentNotFoundException.class)
    public Object notFound(StudentNotFoundException ex) {
        final Map<String, Object> errors = new HashMap<>();
        errors.put("entityName", StudentNotFoundException.ENTITY_NAME);
        errors.put("message", ex.getMessage());
        ex.setCode(HttpStatus.NOT_FOUND.value());
        errors.put("code", ex.getCode().toString());
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public Object notFound(UserNotFoundException ex) {
        final Map<String, Object> errors = new HashMap<>();
        errors.put("entityName", UserNotFoundException.ENTITY_NAME);
        errors.put("message", ex.getMessage());
        ex.setCode(HttpStatus.NOT_FOUND.value());
        errors.put("code", ex.getCode().toString());
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(GradeNotFoundException.class)
    public Object notFound(GradeNotFoundException ex) {
        final Map<String, Object> errors = new HashMap<>();
        errors.put("entityName", GradeNotFoundException.ENTITY_NAME);
        errors.put("message", ex.getMessage());
        ex.setCode(HttpStatus.NOT_FOUND.value());
        errors.put("code", ex.getCode().toString());
        return errors;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public Object exists(UsernameAlreadyExistsException ex) {
        final Map<String, Object> errors = new HashMap<String, Object>();
        errors.put("entityName", UsernameAlreadyExistsException.ENTITY_NAME);
        errors.put("message", ex.getMessage());
        ex.setCode(HttpStatus.CONFLICT.value());
        errors.put("code", ex.getCode().toString());
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CourseNotFoundException.class)
    public Object notFound(CourseNotFoundException ex) {
        final Map<String, Object> errors = new HashMap<>();
        errors.put("entityName", CourseNotFoundException.ENTITY_NAME);
        errors.put("message", ex.getMessage());
        ex.setCode(HttpStatus.NOT_FOUND.value());
        errors.put("code", ex.getCode().toString());
        return errors;
    }
}
