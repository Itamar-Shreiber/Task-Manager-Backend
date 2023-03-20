package com.app.Tasksmanager.clr;

import com.app.Tasksmanager.beans.Status;
import com.app.Tasksmanager.beans.Task;
import com.app.Tasksmanager.beans.User;
import com.app.Tasksmanager.beans.UserType;
import com.app.Tasksmanager.repos.TaskRepository;
import com.app.Tasksmanager.repos.UserRepository;
import com.app.Tasksmanager.services.TaskService;
import com.app.Tasksmanager.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Order(1)
@RequiredArgsConstructor
public class Init implements CommandLineRunner {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskService taskService;

    @Override
    public void run(String... args) throws Exception {
        Task t1 = Task.builder()
                .title("todo")
                .description("todo")
                .tag("todo")
                .time(Timestamp.valueOf(LocalDateTime.now().minusDays(3)))
                .status(Status.TODO)
                .build();
        Task t2 = Task.builder()
                .title("doing java")
                .description("doing java")
                .tag("doing java")
                .time(Timestamp.valueOf(LocalDateTime.now().plusDays(3)))
                .status(Status.DOING)
                .build();
        Task t3 = Task.builder()
                .title("done")
                .description("done")
                .tag("done")
                .time(Timestamp.valueOf(LocalDateTime.now().plusDays(3)))
                .status(Status.DONE)
                .build();

        Task t4 = Task.builder()
                .title("doing")
                .description("doing")
                .tag("doing")
                .time(Timestamp.valueOf(LocalDateTime.now().plusDays(3)))
                .status(Status.DOING)
                .build();


        User admin = User.builder()
                .type(UserType.ADMIN)
                .nickname("admin")
                .email("admin@admin.com")
                .password("admin")
                .build();

        User user1 = User.builder()
                .type(UserType.CUSTOMER)
                .nickname("adir")
                .email("adir@gmail.com")
                .password("1234")
                .tasks(List.of(t1,t2,t3))
                .build();

        User user2 = User.builder()
                .type(UserType.CUSTOMER)
                .nickname("itamar")
                .email("itamar@gmail.com")
                .password("1234")
                .tasks(List.of(t4))
                .build();

        t1.setUser(user1);
        t2.setUser(user1);
        t3.setUser(user1);
        t4.setUser(user2);

        userRepository.saveAll(List.of(admin,user1,user2));
        userRepository.findAll().forEach(System.out::println);
//        System.out.println(t1.getStatus());
//        System.out.println(t2.getStatus());
//        System.out.println(t3.getStatus());
        taskService.getAllTasks(user1.getId(), Status.DOING).forEach(System.out::println);
        taskService.getAllTasks(user1.getId(), Status.DONE).forEach(System.out::println);
        taskService.getAllTasks(user1.getId(), Status.TODO).forEach(System.out::println);
    }
}

