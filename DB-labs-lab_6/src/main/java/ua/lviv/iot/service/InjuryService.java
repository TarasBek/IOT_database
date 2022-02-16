package ua.lviv.iot.service;

import ua.lviv.iot.dto.injury.InjuryDto;
import ua.lviv.iot.model.Injury;

import java.util.List;

public interface InjuryService {

    Injury createInjury(InjuryDto injuryDto);

    List<Injury> getAllInjuries();

    Injury getInjuryById(Integer id);

    Injury updateInjury(InjuryDto injuryDto);

    Injury deleteInjuryById(Integer id);
}
