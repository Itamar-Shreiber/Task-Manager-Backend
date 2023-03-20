package com.app.Tasksmanager.dto;

import com.app.Tasksmanager.beans.Status;
import com.app.Tasksmanager.beans.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskPayloadDto {

    private String title;
    private String description;
    private String group;
    private LocalDateTime when;
    private Status status;
    private User user;
}