package com.example.universitydeploy.Repository;

import com.example.universitydeploy.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUserByEmail(String email);

    @Query(value = "DELETE FROM Users u WHERE u.id = :id")
    void deleteById(@Param("id") long id);

    Users findByEmail(String email);
}