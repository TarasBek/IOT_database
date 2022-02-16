package ua.lviv.iot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.Hospital;

@Repository
public interface HospitalDao extends JpaRepository<Hospital, Integer> {
}
