package ua.lviv.iot.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dto.rescuevehicle.RescueVehicleDto;
import ua.lviv.iot.mappers.RescueVehicleMapper;
import ua.lviv.iot.model.RescueVehicle;
import ua.lviv.iot.service.RescueVehicleService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static ua.lviv.iot.mappers.RescueVehicleMapper.mapRescueVehicleToRescueVehicleDto;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rescue-vehicle")
public class RescueVehicleController {

    private final RescueVehicleService rescueVehicleService;

    @PostMapping
    RescueVehicleDto createRescueVehicle(final @Valid @RequestBody RescueVehicle rescueVehicle) {
        return mapRescueVehicleToRescueVehicleDto(rescueVehicleService.createRescueVehicle(rescueVehicle));
    }

    @GetMapping
    List<RescueVehicleDto> getAllRescueVehicles() {
        return rescueVehicleService.getAllRescueVehicles().stream()
                .map(RescueVehicleMapper::mapRescueVehicleToRescueVehicleDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    RescueVehicleDto getRescueVehicleById(final @PathVariable("id") String number) {
        return mapRescueVehicleToRescueVehicleDto(rescueVehicleService.getRescueVehicleById(number));
    }

    @PutMapping
    RescueVehicleDto updateRescueVehicle(final @Valid @RequestBody RescueVehicle rescueVehicle) {
        return mapRescueVehicleToRescueVehicleDto(rescueVehicleService.updateRescueVehicle(rescueVehicle));
    }

    @DeleteMapping("/{id}")
    RescueVehicleDto deleteRescueVehicleById(final @PathVariable("id") String number) {
        return mapRescueVehicleToRescueVehicleDto(rescueVehicleService.deleteRescueVehicleById(number));
    }
}
