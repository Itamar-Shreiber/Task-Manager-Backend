package com.app.Tasksmanager.services;

import com.app.Tasksmanager.beans.User;
import com.app.Tasksmanager.beans.UserType;
import com.app.Tasksmanager.exceptions.SecuritySystemException;

import java.util.UUID;

public interface TokenService {

    UUID addUser(User user);
    void clearTokens();
    boolean isValid(UUID token, UserType type);
    int getUserID(UUID token) throws SecuritySystemException;
}
