package com.app.Tasksmanager.services;

import com.app.Tasksmanager.dto.LoginResDto;
import com.app.Tasksmanager.exceptions.SecuritySystemException;

public interface UserService {

    void addUser(String email,String password) throws SecuritySystemException;
    LoginResDto getUser(String email, String password) throws SecuritySystemException;
}