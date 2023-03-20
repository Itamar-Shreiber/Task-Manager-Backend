package com.app.Tasksmanager.advice;

import com.app.Tasksmanager.exceptions.SecuritySystemException;
import com.app.Tasksmanager.exceptions.TaskSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TaskControllerAdvice {

    @ExceptionHandler(value = {TaskSystemException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrDetails handleError(Exception e) {
        return ErrDetails.builder().value(e.getMessage()).build();
    }

    @ExceptionHandler(value = {SecuritySystemException.class})
    public ResponseEntity<?> handleError(SecuritySystemException e) {
        ErrDetails err = ErrDetails.builder().value(e.getMessage()).build();
        HttpStatus status = e.getStatus();
        return new ResponseEntity<>(err, status);
    }
}