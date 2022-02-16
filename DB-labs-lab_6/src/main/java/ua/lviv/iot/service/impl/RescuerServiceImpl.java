package ua.lviv.iot.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dao.RescuerDao;
import ua.lviv.iot.exception.NoDataFoundException;
import ua.lviv.iot.model.Rescuer;
import ua.lviv.iot.service.RescuerService;

import java.util.List;

@Service
@AllArgsConstructor
public class RescuerServiceImpl implements RescuerService {

    private final RescuerDao rescuerDao;

    @Override
    public Rescuer createRescuer(Rescuer rescuer) {
        return rescuerDao.save(rescuer);
    }

    @Override
    public List<Rescuer> getAllRescuers() {
        return rescuerDao.findAll();
    }

    @Override
    public Rescuer getRescuerById(Integer id) {
        return rescuerDao.findById(id)
                .orElseThrow(() -> new NoDataFoundException("Entity with id: " + id + " not found"));
    }

    @Override
    public Rescuer updateRescuer(Rescuer rescuer) {
        getRescuerById(rescuer.getId()); // will throw exception if entity doesn't exist
        return rescuerDao.save(rescuer);
    }

    /**
     * Deletes with CascadeType=REMOVE
     */
    @Override
    public Rescuer deleteRescuerById(Integer id) {
        Rescuer rescuer = getRescuerById(id); // throws runtime exception if not found by id
        rescuerDao.deleteById(id);
        return rescuer;
    }
}
