package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.models.Student;
import ua.lviv.iot.models.Subject;
import ua.lviv.iot.repository.StudentRepository;
import ua.lviv.iot.repository.SubjectRepository;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student findById(Long id){
        return studentRepository.getOne(id);
    }

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public Student saveStudent(Student subject){
        return studentRepository.save(subject);
    }

    public void deleteById(Long id){
        studentRepository.deleteById(id);
    }
}
