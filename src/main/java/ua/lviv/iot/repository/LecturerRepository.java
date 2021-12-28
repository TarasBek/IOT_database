package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.iot.models.Lecturer;
import ua.lviv.iot.models.Subject;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
}
