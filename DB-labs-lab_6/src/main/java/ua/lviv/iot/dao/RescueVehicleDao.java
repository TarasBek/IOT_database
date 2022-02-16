package ua.lviv.iot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.RescueVehicle;

@Repository
public interface RescueVehicleDao extends JpaRepository<RescueVehicle, String> {
}
