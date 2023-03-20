package com.app.Tasksmanager.exceptions;

public class TaskSystemException extends Exception{

    public TaskSystemException(ErrMsg errMsg) {
        super(errMsg.getMessage());
    }
}
