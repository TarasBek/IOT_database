package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.models.Speaker;
import ua.lviv.iot.models.Subject;
import ua.lviv.iot.repository.SpeakerRepository;
import ua.lviv.iot.repository.SubjectRepository;

import java.util.List;

@Service
public class SpeakerService {
    private final SpeakerRepository speakerRepository;

    @Autowired
    public SpeakerService(SpeakerRepository speakerRepository){
        this.speakerRepository = speakerRepository;
    }

    public Speaker findById(Long id){
        return speakerRepository.getOne(id);
    }

    public List<Speaker> findAll(){
        return speakerRepository.findAll();
    }

    public Speaker saveSpeaker(Speaker speaker){
        return speakerRepository.save(speaker);
    }

    public void deleteById(Long id){
        speakerRepository.deleteById(id);
    }
}
