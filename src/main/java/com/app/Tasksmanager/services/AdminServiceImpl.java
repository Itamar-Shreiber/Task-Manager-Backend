package com.app.Tasksmanager.services;

import com.app.Tasksmanager.beans.User;
import com.app.Tasksmanager.beans.UserType;
import com.app.Tasksmanager.exceptions.SecuritySystemException;
import com.app.Tasksmanager.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Override
    public List<User> getAllUsers() {
        List<User> users=new ArrayList<>();
        for (User user : userRepository.findAll()) {
            if (user.getType().equals(UserType.CUSTOMER)) {
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public void deleteUser(int userId) throws SecuritySystemException {
        userRepository.deleteById(userId);
    }
}
