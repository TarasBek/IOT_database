package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.models.ClusterProgram;
import ua.lviv.iot.repository.ClusterProgramRepository;
import java.util.List;

@Service
public class ClusterProgramService {

    private final ClusterProgramRepository clusterProgramRepository;

    @Autowired
    public ClusterProgramService(ClusterProgramRepository clusterProgramRepository){
        this.clusterProgramRepository = clusterProgramRepository;
    }

    public ClusterProgram findById(Long id){
        return clusterProgramRepository.getOne(id);
    }

    public List<ClusterProgram> findAll(){
        return clusterProgramRepository.findAll();
    }

    public ClusterProgram saveClusterProgram(ClusterProgram clusterProgram){
        return clusterProgramRepository.save(clusterProgram);
    }

    public void deleteById(Long id){
        clusterProgramRepository.deleteById(id);
    }


}
