package com.app.Tasksmanager.security;

import com.app.Tasksmanager.beans.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Information {

    private int id;
    private UserType type;
    private LocalDateTime time;
}
