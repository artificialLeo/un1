package com.example.universitydeploy.Service;

import com.example.universitydeploy.Models.Student;
import com.example.universitydeploy.Repository.StudentRepository;
import com.example.universitydeploy.Repository.SubjectRepository;
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
public class StudentService {

    private final StudentRepository studentRepository;

    private final SubjectRepository subjectRepository;

    private final TimetableRepository timetableRepository;

    public void save(Student student) {
        studentRepository.save(student);
    }
    public void saveAll(List<Student> students) {
        studentRepository.saveAll(students);
    }

    public Page<Student> findAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return studentRepository.findAll(pageable);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(long id) {
        return studentRepository.findById(id);
    }
    public void deleteById(long id) {
        studentRepository.deleteById(id);
    }


}
