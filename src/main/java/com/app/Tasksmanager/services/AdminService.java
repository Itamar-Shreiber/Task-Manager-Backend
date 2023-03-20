package com.app.Tasksmanager.services;

import com.app.Tasksmanager.beans.User;
import com.app.Tasksmanager.exceptions.SecuritySystemException;

import java.util.List;
import java.util.UUID;

public interface AdminService {
    List<User> getAllUsers();
    void deleteUser(int userId) throws SecuritySystemException;

}
