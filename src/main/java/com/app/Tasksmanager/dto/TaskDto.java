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
public class TaskDto {
    private int id;
    private String title;
    private String description;
    private String group;
    private LocalDateTime when;
    private Status status;
    private User user;

    public TaskDto(TaskPayloadDto payload) {
        this.id = 0;
        this.title = payload.getTitle();
        this.description = payload.getDescription();
        this.group = payload.getGroup();
        this.when = payload.getWhen();
        this.status = payload.getStatus();
        this.user = payload.getUser();
    }
}

