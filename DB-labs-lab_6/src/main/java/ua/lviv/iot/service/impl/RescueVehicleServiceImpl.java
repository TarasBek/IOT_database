package ua.lviv.iot.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dao.CallHasRescueVehicleDao;
import ua.lviv.iot.dao.RescueVehicleDao;
import ua.lviv.iot.exception.EntityAlreadyExistsException;
import ua.lviv.iot.exception.ForeignKeyConstraintException;
import ua.lviv.iot.exception.NoDataFoundException;
import ua.lviv.iot.model.RescueVehicle;
import ua.lviv.iot.service.RescueVehicleService;

import java.util.List;

@Service
@AllArgsConstructor
public class RescueVehicleServiceImpl implements RescueVehicleService {

    private final RescueVehicleDao rescueVehicleDao;
    private final CallHasRescueVehicleDao callHasRescueVehicleDao;

    @Override
    public RescueVehicle createRescueVehicle(RescueVehicle rescueVehicle) {
        String id = rescueVehicle.getNumber();
        if (rescueVehicleDao.existsById(id)) {
            throw new EntityAlreadyExistsException("Entity with id: " + id + " already exists");
        }
        return rescueVehicleDao.save(rescueVehicle);
    }

    @Override
    public List<RescueVehicle> getAllRescueVehicles() {
        return rescueVehicleDao.findAll();
    }

    @Override
    public RescueVehicle getRescueVehicleById(String number) {
        return rescueVehicleDao.findById(number)
                .orElseThrow(() -> new NoDataFoundException("Entity with id: " + number + " not found"));
    }

    @Override
    public RescueVehicle updateRescueVehicle(RescueVehicle rescueVehicle) {
        getRescueVehicleById(rescueVehicle.getNumber()); // throws exception if entity doesn't exist
        return rescueVehicleDao.save(rescueVehicle);
    }

    /**
     * Deletes only if there is no foreign key associations
     */
    @Override
    public RescueVehicle deleteRescueVehicleById(String number) {
        RescueVehicle oldRescueVehicle = getRescueVehicleById(number); // throws runtime exception if not found by id
        if (callHasRescueVehicleDao.countCallHasRescueVehiclesByRescueVehicle_Number(number) > 0) {
            throw new ForeignKeyConstraintException("Unable to delete this entity because its id is used in child table");
        }
        rescueVehicleDao.deleteById(number);
        return oldRescueVehicle;
    }
}
