package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.models.ClusterProgram;
import ua.lviv.iot.models.Subject;
import ua.lviv.iot.repository.ClusterProgramRepository;
import ua.lviv.iot.repository.SubjectRepository;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
    }

    public Subject findById(Long id){
        return subjectRepository.getOne(id);
    }

    public List<Subject> findAll(){
        return subjectRepository.findAll();
    }

    public Subject saveSubject(Subject subject){
        return subjectRepository.save(subject);
    }

    public void deleteById(Long id){
        subjectRepository.deleteById(id);
    }
}
