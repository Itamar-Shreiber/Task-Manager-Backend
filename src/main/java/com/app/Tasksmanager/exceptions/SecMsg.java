package com.app.Tasksmanager.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum SecMsg {

    EMAIL_ALREADY_EXIST("email already exist", HttpStatus.CONFLICT),
    INVALID_EMAIL_OR_PASS("invalid email or password",HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN("invalid token plese login again",HttpStatus.UNAUTHORIZED),
    ID_NOT_FOUND("id not found",HttpStatus.UNAUTHORIZED);

    private String message;
    private HttpStatus status;

    SecMsg(String message,HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}

