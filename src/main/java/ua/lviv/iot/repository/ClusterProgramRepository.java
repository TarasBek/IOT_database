package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.iot.models.ClusterProgram;
import ua.lviv.iot.models.Subject;

public interface ClusterProgramRepository extends JpaRepository<ClusterProgram, Long> {

}
