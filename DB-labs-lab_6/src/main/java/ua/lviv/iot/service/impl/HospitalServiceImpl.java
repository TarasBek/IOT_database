package ua.lviv.iot.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dao.CallHasRescuerDao;
import ua.lviv.iot.dao.HospitalDao;
import ua.lviv.iot.dao.InjuryDao;
import ua.lviv.iot.exception.NoDataFoundException;
import ua.lviv.iot.model.Hospital;
import ua.lviv.iot.service.HospitalService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class HospitalServiceImpl implements HospitalService {

    private final HospitalDao hospitalDao;
    private final InjuryDao injuryDao;
    private final CallHasRescuerDao callHasRescuerDao;

    @Override
    public Hospital createHospital(Hospital hospital) {
        return hospitalDao.save(hospital);
    }

    @Override
    public List<Hospital> getAllHospitals() {
        return hospitalDao.findAll();
    }

    @Override
    public Hospital getHospitalById(Integer id) {
        return hospitalDao.findById(id)
                .orElseThrow(() -> new NoDataFoundException("Entity with id: " + id + " not found"));
    }

    @Override
    public Hospital updateHospital(Hospital hospital) {
        getHospitalById(hospital.getId()); // will throw exception if entity doesn't exist
        return hospitalDao.save(hospital);
    }

    /**
     * Delete manually by telling what after what to delete
     */
    @Override
    public Hospital deleteHospitalById(Integer id) {
        Hospital hospital = getHospitalById(id); // throws runtime exception if not found by id
        for (Integer injuryId : injuryDao.findInjury_IdsByHospital_id(id)) { // removes all the associated injuries
            callHasRescuerDao.deleteCallHasRescuerByInjury_Id(injuryId);
            injuryDao.deleteById(injuryId);
        }
        hospitalDao.deleteById(id);
        return hospital;
    }
}
