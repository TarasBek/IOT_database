package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.iot.models.Student;
import ua.lviv.iot.models.Subject;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
