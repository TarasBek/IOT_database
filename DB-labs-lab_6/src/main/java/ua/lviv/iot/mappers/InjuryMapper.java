package ua.lviv.iot.mappers;

import ua.lviv.iot.dto.injury.InjuryDto;
import ua.lviv.iot.model.Injury;

public class InjuryMapper {

    private InjuryMapper() {
    }

    public static InjuryDto mapInjuryToInjuryDto(Injury injury) {
        return new InjuryDto(
                injury.getId(),
                injury.getDescription(),
                injury.getDiagnosis(),
                injury.getHospital().getId()
        );
    }
}
