package com.example.universitydeploy.Service;

import com.example.universitydeploy.Models.Teacher;
import com.example.universitydeploy.Repository.SubjectRepository;
import com.example.universitydeploy.Repository.TeacherRepository;
import com.example.universitydeploy.Repository.TimetableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    private final SubjectRepository subjectRepository;

    private final TimetableRepository timetableRepository;

    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }
    public void saveAll(List<Teacher> teachers) {
        teacherRepository.saveAll(teachers);
    }

    public Page<Teacher> findAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return teacherRepository.findAll(pageable);
    }

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> findById(long id) {
        return teacherRepository.findById(id);
    }

    public void deleteById(long id) {
        teacherRepository.deleteById(id);
    }

    public Teacher findByEmail(String email) {
        return teacherRepository.findAll().stream()
                .filter(teacher -> teacher.getEmail().equals(email))
                .findFirst().orElse(null);
    }
}
