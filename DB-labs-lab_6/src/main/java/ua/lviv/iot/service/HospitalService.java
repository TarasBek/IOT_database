package ua.lviv.iot.service;

import ua.lviv.iot.model.Hospital;
import ua.lviv.iot.model.Reporter;

import java.util.List;

public interface HospitalService {

    Hospital createHospital(Hospital hospital);

    List<Hospital> getAllHospitals();

    Hospital getHospitalById(Integer id);

    Hospital updateHospital(Hospital hospital);

    Hospital deleteHospitalById(Integer id);
}
