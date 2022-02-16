package ua.lviv.iot.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dto.hospital.HospitalDto;
import ua.lviv.iot.mappers.HospitalMapper;
import ua.lviv.iot.model.Hospital;
import ua.lviv.iot.service.HospitalService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static ua.lviv.iot.mappers.HospitalMapper.mapHospitalToHospitalDto;

@RestController
@AllArgsConstructor
@RequestMapping("/api/hospital")
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping
    HospitalDto createHospital(final @Valid @RequestBody Hospital hospital) {
        return mapHospitalToHospitalDto(hospitalService.createHospital(hospital));
    }

    @GetMapping
    List<HospitalDto> getAllHospitals() {
        return hospitalService.getAllHospitals().stream()
                .map(HospitalMapper::mapHospitalToHospitalDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    HospitalDto getHospitalById(final @PathVariable("id") Integer id) {
        return mapHospitalToHospitalDto(hospitalService.getHospitalById(id));
    }

    @PutMapping
    HospitalDto updateHospital(final @Valid @RequestBody Hospital hospital) {
        return mapHospitalToHospitalDto(hospitalService.updateHospital(hospital));
    }

    @DeleteMapping("/{id}")
    HospitalDto deleteHospitalById(final @PathVariable("id") Integer id) {
        return mapHospitalToHospitalDto(hospitalService.deleteHospitalById(id));
    }
}
