package com.app.Tasksmanager.repos;

import com.app.Tasksmanager.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByEmailAndPassword(String email,String password);
    User findTop1ByEmailAndPassword(String email,String password);
    boolean existsByEmail(String email);
}