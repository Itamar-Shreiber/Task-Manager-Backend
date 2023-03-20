package com.app.Tasksmanager.jobs;

import com.app.Tasksmanager.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenRemoval {

    private final TokenService tokenService;

    @Scheduled(fixedRate = 60*1000)
    public void yalla(){
        tokenService.clearTokens();
    }

}

