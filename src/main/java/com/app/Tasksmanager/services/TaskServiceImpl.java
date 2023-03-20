package com.app.Tasksmanager.services;

import com.app.Tasksmanager.beans.Status;
import com.app.Tasksmanager.beans.Task;
import com.app.Tasksmanager.beans.User;
import com.app.Tasksmanager.dto.TaskDto;
import com.app.Tasksmanager.dto.TaskPayloadDto;
import com.app.Tasksmanager.exceptions.ErrMsg;
import com.app.Tasksmanager.exceptions.SecMsg;
import com.app.Tasksmanager.exceptions.SecuritySystemException;
import com.app.Tasksmanager.exceptions.TaskSystemException;
import com.app.Tasksmanager.mappers.TaskMapper;
import com.app.Tasksmanager.repos.TaskRepository;
import com.app.Tasksmanager.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;
    private final TokenService tokenService;


    @Override
    public TaskDto addTask(TaskPayloadDto payload, UUID token) throws SecuritySystemException {
        User user=userRepository.findById(tokenService.getUserID(token)).orElseThrow(()-> new SecurityException(String.valueOf(SecMsg.ID_NOT_FOUND)));
        TaskDto dto = new TaskDto(payload);
        Task dao = taskMapper.toDao(dto);
        dao.setUser(user);
        Task daoRes = taskRepository.save(dao);
        TaskDto dtoRes = taskMapper.toDto(daoRes);
        dtoRes.setUser(user);
        return dtoRes;
    }

    @Override
    public TaskDto updateTask(int taskId, TaskPayloadDto taskPayloadDto,UUID token) throws TaskSystemException, SecuritySystemException {
        if (!taskRepository.existsById(taskId)) {
            throw new TaskSystemException(ErrMsg.TASK_ID_NOT_FOUND);
        }
        User user=userRepository.findById(tokenService.getUserID(token)).orElseThrow(()-> new SecurityException(String.valueOf(SecMsg.ID_NOT_FOUND)));
        TaskDto dto = new TaskDto(taskPayloadDto);
        dto.setId(taskId);
        Task dao = taskMapper.toDao(dto);
        dao.setUser(user);
        Task daoRes = taskRepository.saveAndFlush(dao);
        TaskDto dtoRes = taskMapper.toDto(daoRes);
        dtoRes.setUser(user);
        return dtoRes;
    }

    @Override
    public void deleteTask(int taskId) {
        taskRepository.deleteById(taskId);
    }

//    @Override
//    public List<TaskDto> getAllTasks() {
//        List<Task> daoList = taskRepository.findAll();
//        List<TaskDto> dtoList = taskMapper.toDtoList(daoList);
//        return dtoList;
//    }

    @Override
    public List<TaskDto> getAllTasks(int userId, Status status) {
        List<Task> daoList = taskRepository.findByUserId(userId);
        List<TaskDto> dtoList = taskMapper.toDtoList(daoList);
        List<TaskDto> list=new ArrayList<>();
        for (TaskDto t:dtoList) {
            if(t.getStatus().equals(status)){
                list.add(t);
            }
        }
        return list;
    }

    @Override
    public TaskDto getSingleTask(int taskId) throws TaskSystemException {
        Task dao = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskSystemException(ErrMsg.TASK_ID_NOT_FOUND));

        TaskDto dto = taskMapper.toDto(dao);
        return dto;
    }

    @Override
    public int count() {
        return (int) taskRepository.count();
    }

    @Override
    public boolean isTaskExist(int taskId) {
        return taskRepository.existsById(taskId);
    }
}
