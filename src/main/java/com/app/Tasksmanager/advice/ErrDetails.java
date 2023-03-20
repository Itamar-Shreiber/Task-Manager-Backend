package com.app.Tasksmanager.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrDetails {
    private final String key = "Task Manager";
    private String value;
}
