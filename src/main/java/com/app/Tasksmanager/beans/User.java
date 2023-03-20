package com.app.Tasksmanager.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    private String nickname;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user",cascade = CascadeType.PERSIST)
    private List<Task> tasks;

    private UserType type;
}

