package com.app.Tasksmanager.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    private String tag;

    private Timestamp time;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private User user;

}