package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.models.Response;
import ua.lviv.iot.models.Subject;
import ua.lviv.iot.repository.ResponseRepository;
import ua.lviv.iot.repository.SubjectRepository;

import java.util.List;

@Service
public class ResponseService {
    private final ResponseRepository responseRepository;

    @Autowired
    public ResponseService(ResponseRepository responseRepository){
        this.responseRepository = responseRepository;
    }

    public Response findById(Long id){
        return responseRepository.getOne(id);
    }

    public List<Response> findAll(){
        return responseRepository.findAll();
    }

    public Response saveResponse(Response response){
        return responseRepository.save(response);
    }

    public void deleteById(Long id){
        responseRepository.deleteById(id);
    }
}
