package com.example.universitydeploy.Service;

import com.example.universitydeploy.Models.*;
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
public class TimetableService {
    private final TimetableRepository timetableRepository;
    private final SubjectRepository subjectRepository;

    public void save(Timetable timetable) {
        timetableRepository.save(timetable);
    }
    public void saveAll(List<Timetable> timetables) {
        timetableRepository.saveAll(timetables);
    }

    public Page<Timetable> findAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return timetableRepository.findAll(pageable);
    }

    public List<Timetable> findAll() {
        return timetableRepository.findAll();
    }

    public Optional<Timetable> findById(long id) {
        return timetableRepository.findById(id);
    }

    public void deleteById(long id) {
        timetableRepository.deleteById(id);
    }

    public void updateTimetable(Timetable timetable, List<Subject> subjects) {
        Timetable timetableById = findById(timetable.getTimetableId()).orElseThrow();
        timetableById.setSubjectList(subjects);

        timetableRepository.save(timetableById);
    }
}
