package ua.lviv.iot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.Call;

@Repository
public interface CallDao extends JpaRepository<Call, Integer> {
}
