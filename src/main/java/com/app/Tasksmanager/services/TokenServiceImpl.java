package com.app.Tasksmanager.services;

import com.app.Tasksmanager.beans.User;
import com.app.Tasksmanager.beans.UserType;
import com.app.Tasksmanager.exceptions.SecMsg;
import com.app.Tasksmanager.exceptions.SecuritySystemException;
import com.app.Tasksmanager.security.Information;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final Map<UUID, Information> map;

    @Override
    public UUID addUser(User user) {

        UUID token = UUID.randomUUID();
        Information information = Information.builder()
                .id(user.getId())
                .type(user.getType())
                .time(LocalDateTime.now())
                .build();

// TODO: 29/12/2022 consider to remove previous insatcnce in the map to this user
        map.put(token, information);
        return token;
    }

    @Override
    public void clearTokens() {
        map.values().removeIf(obj->obj.getTime().isBefore(LocalDateTime.now().minusMinutes(30)));
    }

    @Override
    public boolean isValid(UUID token, UserType type) {
        Information info = map.get(token);
        if(info!=null){
            return info.getType().equals(type);
        }
        return false;
    }

    @Override
    public int getUserID(UUID token) throws SecuritySystemException {

        Information info = map.get(token);
        if(info!=null){
            return info.getId();
        }
        throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
    }
}
