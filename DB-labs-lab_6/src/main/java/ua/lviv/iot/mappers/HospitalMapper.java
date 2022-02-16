package ua.lviv.iot.mappers;

import ua.lviv.iot.dto.hospital.HospitalDto;
import ua.lviv.iot.model.Hospital;

public class HospitalMapper {

    private HospitalMapper() {
    }

    public static HospitalDto mapHospitalToHospitalDto(Hospital hospital) {
        return new HospitalDto(
                hospital.getId(),
                hospital.getName()
        );
    }
}
