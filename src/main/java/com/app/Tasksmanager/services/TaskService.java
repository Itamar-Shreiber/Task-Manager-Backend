package com.app.Tasksmanager.services;

import com.app.Tasksmanager.beans.Status;
import com.app.Tasksmanager.dto.TaskDto;
import com.app.Tasksmanager.dto.TaskPayloadDto;
import com.app.Tasksmanager.exceptions.SecuritySystemException;
import com.app.Tasksmanager.exceptions.TaskSystemException;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    TaskDto addTask(TaskPayloadDto taskPayloadDto, UUID token) throws SecuritySystemException;
    TaskDto updateTask(int taskId,TaskPayloadDto task,UUID token) throws TaskSystemException, SecuritySystemException;
    void deleteTask(int taskId);
    TaskDto getSingleTask(int taskId) throws TaskSystemException;
    int count();
    boolean isTaskExist(int taskId);
    List<TaskDto> getAllTasks(int userId, Status status);
}
