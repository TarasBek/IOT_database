package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.iot.models.ClusterProgram;
import ua.lviv.iot.models.Speaker;

public interface SpeakerRepository  extends JpaRepository<Speaker, Long> {
}
