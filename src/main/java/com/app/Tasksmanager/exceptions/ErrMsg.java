package com.app.Tasksmanager.exceptions;

import lombok.Getter;

@Getter
public enum ErrMsg {
    TASK_ID_NOT_FOUND("no such task id"),
    TASK_ID_ALREADY_EXIST("task is already exist");

    private String message;

    ErrMsg(String message) {
        this.message = message;
    }
}
