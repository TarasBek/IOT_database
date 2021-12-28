package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.models.ClusterProgram;
import ua.lviv.iot.models.Lecturer;
import ua.lviv.iot.repository.ClusterProgramRepository;
import ua.lviv.iot.repository.LecturerRepository;

import java.util.List;

@Service
public class LecturerService {

    private final LecturerRepository lecturerRepository;

    @Autowired
    public LecturerService(LecturerRepository lecturerRepository){
        this.lecturerRepository = lecturerRepository;
    }

    public Lecturer findById(Long id){
        return lecturerRepository.getOne(id);
    }

    public List<Lecturer> findAll(){
        return lecturerRepository.findAll();
    }

    public Lecturer saveLecturer(Lecturer lecturer){
        return lecturerRepository.save(lecturer);
    }

    public void deleteById(Long id){
        lecturerRepository.deleteById(id);
    }

}
