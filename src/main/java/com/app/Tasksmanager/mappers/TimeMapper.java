package com.app.Tasksmanager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Mapper
public interface TimeMapper {

    TimeMapper INSTANCE = Mappers.getMapper(TimeMapper.class);

    default Timestamp toTimestamp(LocalDateTime localDateTime){
        return Timestamp.valueOf(localDateTime);
    }

    default LocalDateTime toLocalDateTime(Timestamp timestamp){
        return timestamp.toLocalDateTime();
    }
}