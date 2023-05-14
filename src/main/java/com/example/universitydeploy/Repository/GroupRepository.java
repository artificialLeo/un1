package com.example.universitydeploy.Repository;

import com.example.universitydeploy.Models.UniversityGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<UniversityGroup, Long> {
    List<UniversityGroup> findByTeacherId(long id);
}
