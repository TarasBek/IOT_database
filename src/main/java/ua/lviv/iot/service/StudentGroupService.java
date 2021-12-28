package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.models.StudentGroup;
import ua.lviv.iot.models.Subject;
import ua.lviv.iot.repository.StudentGroupRepository;
import ua.lviv.iot.repository.SubjectRepository;

import java.util.List;

@Service
public class StudentGroupService {
    private final StudentGroupRepository studentGroupRepository;

    @Autowired
    public StudentGroupService(StudentGroupRepository studentGroupRepository){
        this.studentGroupRepository = studentGroupRepository;
    }

    public StudentGroup findById(Long id){
        return studentGroupRepository.getOne(id);
    }

    public List<StudentGroup> findAll(){
        return studentGroupRepository.findAll();
    }

    public StudentGroup saveStudentGroup(StudentGroup studentGroup){
        return studentGroupRepository.save(studentGroup);
    }

    public void deleteById(Long id){
        studentGroupRepository.deleteById(id);
    }
}
