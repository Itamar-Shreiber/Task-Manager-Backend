package com.app.Tasksmanager.controllers;

import com.app.Tasksmanager.dto.LoginReqDto;
import com.app.Tasksmanager.dto.LoginResDto;
import com.app.Tasksmanager.dto.UserReqDto;
import com.app.Tasksmanager.exceptions.SecuritySystemException;
import com.app.Tasksmanager.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/login")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody UserReqDto req) throws SecuritySystemException {
        String email = req.getEmail();
        String password = req.getPassword();
        userService.addUser(email,password);
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResDto login(@RequestBody LoginReqDto req) throws SecuritySystemException {
        String email = req.getEmail();
        String pass = req.getPassword();
        return userService.getUser(email,pass);
    }
}

