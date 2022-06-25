package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLoginAndPassword(String login, String password);


    User findByLogin(String login);

    @Query(value = "SELECT COUNT(*) FROM users WHERE login = :login", nativeQuery = true)
    int getCountLogin(@Param("login") String login);


}
