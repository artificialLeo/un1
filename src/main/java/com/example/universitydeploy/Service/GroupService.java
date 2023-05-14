package com.example.universitydeploy.Service;

import com.example.universitydeploy.Models.Teacher;
import com.example.universitydeploy.Models.UniversityGroup;
import com.example.universitydeploy.Repository.GroupRepository;
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
public class GroupService {
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;

    public void save(UniversityGroup group) {
        groupRepository.save(group);
    }
    public void saveAll(List<UniversityGroup> groups) {
        groupRepository.saveAll(groups);
    }

    public Page<UniversityGroup> findAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return groupRepository.findAll(pageable);
    }

    public List<UniversityGroup> findAll() {
        return groupRepository.findAll();
    }
    public List<UniversityGroup> findAllByID(long id) {
        return groupRepository.findByTeacherId(id);
    }

    public Optional<UniversityGroup> findById(long id) {
        return groupRepository.findById(id);
    }

    public void deleteById(long id) {
        groupRepository.deleteById(id);
    }

    public void updateGroup(UniversityGroup group, Optional<Teacher> teacher) {
        UniversityGroup groupById = findById(group.getGroupId()).orElseThrow();
        groupById.setGroupName(group.getGroupName());

        if (teacher.isPresent()) {
            Teacher teacherFromRepository = teacherRepository.findById(teacher.get().getId()).orElseThrow();
            groupById.setTeacher(teacherFromRepository);
        }

        groupRepository.save(groupById);
    }

    public void deleteGroup(UniversityGroup universityGroup) {
        groupRepository.delete(universityGroup);
    }
}
