package com.app.Tasksmanager.controllers;

import com.app.Tasksmanager.beans.User;
import com.app.Tasksmanager.beans.UserType;
import com.app.Tasksmanager.exceptions.SecMsg;
import com.app.Tasksmanager.exceptions.SecuritySystemException;
import com.app.Tasksmanager.services.AdminService;
import com.app.Tasksmanager.services.TaskService;
import com.app.Tasksmanager.services.TokenService;
import com.app.Tasksmanager.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final TaskService taskService;
    private final TokenService tokenService;
    private final AdminService adminService;

    @GetMapping("users")
    public List<User> getAllUsers(@RequestHeader("Authorization") UUID token) throws SecuritySystemException {
        if(!tokenService.isValid(token, UserType.ADMIN)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        return adminService.getAllUsers();
    }
    @DeleteMapping("users/{userId}")
    public void deleteUser(@PathVariable int userId,@RequestHeader("Authorization") UUID token) throws SecuritySystemException {
        if(!tokenService.isValid(token, UserType.ADMIN)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        adminService.deleteUser(userId);
    }
}
