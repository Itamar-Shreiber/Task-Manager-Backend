package com.app.Tasksmanager.services;

import com.app.Tasksmanager.beans.User;
import com.app.Tasksmanager.beans.UserType;
import com.app.Tasksmanager.dto.LoginResDto;
import com.app.Tasksmanager.exceptions.SecMsg;
import com.app.Tasksmanager.exceptions.SecuritySystemException;
import com.app.Tasksmanager.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Override
    public void addUser(String email, String password) throws SecuritySystemException {

        if (userRepository.existsByEmail(email)) {
            throw new SecuritySystemException(SecMsg.EMAIL_ALREADY_EXIST);
        }
        User user = User.builder()
                .email(email)
                .password(password)
                .type(UserType.CUSTOMER)
                .build();

        userRepository.save(user);
        userRepository.findAll().forEach(System.out::println);
    }

    @Override
    public LoginResDto getUser(String email, String password) throws SecuritySystemException {
        if (!userRepository.existsByEmailAndPassword(email, password)) {
            throw new SecuritySystemException(SecMsg.INVALID_EMAIL_OR_PASS);
        }

        User user = userRepository.findTop1ByEmailAndPassword(email, password);
        UUID token = tokenService.addUser(user);
        LoginResDto res = LoginResDto.builder()
                .email(email)
                .token(token).build();
        return res;
    }

}
