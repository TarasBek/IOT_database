package ua.lviv.iot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.Reporter;

@Repository
public interface ReporterDao extends JpaRepository<Reporter, String> {
}
