package com.app.Tasksmanager.controllers;

import com.app.Tasksmanager.beans.Status;
import com.app.Tasksmanager.beans.UserType;
import com.app.Tasksmanager.dto.TaskDto;
import com.app.Tasksmanager.dto.TaskPayloadDto;
import com.app.Tasksmanager.exceptions.SecMsg;
import com.app.Tasksmanager.exceptions.SecuritySystemException;
import com.app.Tasksmanager.exceptions.TaskSystemException;
import com.app.Tasksmanager.services.TaskService;
import com.app.Tasksmanager.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final TaskService taskService;
    private final TokenService tokenService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto addTask(@RequestBody TaskPayloadDto task, @RequestHeader("Authorization") UUID token) throws TaskSystemException, SecuritySystemException {
        if(!tokenService.isValid(token, UserType.CUSTOMER)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        return taskService.addTask(task,token);
    }

    @PutMapping("/{taskId}")
    public TaskDto updateTask(@PathVariable int taskId,@RequestBody TaskPayloadDto taskPayloadDto, @RequestHeader("Authorization") UUID token) throws TaskSystemException, SecuritySystemException {
        if(!tokenService.isValid(token, UserType.CUSTOMER)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        System.out.println(taskPayloadDto);
        return taskService.updateTask(taskId, taskPayloadDto,token);
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable int taskId){
        taskService.deleteTask(taskId);
    }

    @GetMapping
    public List<TaskDto> getAllTasks(@RequestParam Status status, @RequestHeader("Authorization") UUID token) throws SecuritySystemException {
        if(!tokenService.isValid(token, UserType.CUSTOMER)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        int userId = tokenService.getUserID(token);
        return taskService.getAllTasks(userId,status);
    }

    @GetMapping("/{taskId}")
    public TaskDto getSingleTask(@PathVariable int taskId) throws TaskSystemException{
        return taskService.getSingleTask(taskId);
    }
    @GetMapping("count")
    public int count(){
        return taskService.count();
    }

    @GetMapping("/{taskId}/exists")
    public boolean isTaskExist(@PathVariable int taskId){
        return taskService.isTaskExist(taskId);
    }
}
