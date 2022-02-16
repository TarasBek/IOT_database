package ua.lviv.iot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.dto.callhasrescuevehicle.CallHasRescueVehicleDto;
import ua.lviv.iot.model.CallHasRescueVehicle;
import ua.lviv.iot.model.compositekey.CallRescueVehicleId;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface CallHasRescueVehicleDao extends JpaRepository<CallHasRescueVehicle, CallRescueVehicleId> {

    Integer countCallHasRescueVehiclesByRescueVehicle_Number(String number);
}
