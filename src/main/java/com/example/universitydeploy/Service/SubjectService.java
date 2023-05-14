package com.example.universitydeploy.Service;

import com.example.universitydeploy.Models.Subject;
import com.example.universitydeploy.Repository.StudentRepository;
import com.example.universitydeploy.Repository.SubjectRepository;
import com.example.universitydeploy.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;

    public void save(Subject subject) {
        subjectRepository.save(subject);
    }
    public void saveAll(List<Subject> subjects) {
        subjectRepository.saveAll(subjects);
    }

    public Page<Subject> findAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return subjectRepository.findAll(pageable);
    }

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> findById(long id) {
        return subjectRepository.findById(id);
    }

    public void deleteById(long id) {
        subjectRepository.deleteById(id);
    }

    public List<Subject> findAllById(List<Long> subjectIds) {
        return subjectRepository.findAllById(subjectIds);
    }
}
