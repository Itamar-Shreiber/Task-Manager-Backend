package com.app.Tasksmanager.mappers;
import com.app.Tasksmanager.beans.Task;
import com.app.Tasksmanager.dto.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(uses = {TimeMapper.class})
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    //    @Mapping(source = "id",target = "id")
//    @Mapping(source = "title",target = "title")
//    @Mapping(source = "description",target = "description")
    @Mapping(source = "tag",target = "group")
    @Mapping(source = "time",target = "when")
    TaskDto toDto(Task task);

    //    @Mapping(source = "id",target = "id")
//    @Mapping(source = "title",target = "title")
//    @Mapping(source = "description",target = "description")
    @Mapping(source = "group",target = "tag")
    @Mapping(source = "when",target = "time")
    Task toDao(TaskDto taskDto);

    default List<TaskDto> toDtoList(List<Task> tasks){
        return tasks.stream().map(this::toDto).collect(Collectors.toList());
    }

    default List<Task> toDaoList(List<TaskDto> taskDto){
        return taskDto.stream().map(this::toDao).collect(Collectors.toList());
    }
}
