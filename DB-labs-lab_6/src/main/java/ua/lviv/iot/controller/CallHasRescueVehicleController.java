package ua.lviv.iot.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dto.callhasrescuevehicle.CallHasRescueVehicleDto;
import ua.lviv.iot.mappers.CallHasRescueVehicleMapper;
import ua.lviv.iot.model.compositekey.CallRescueVehicleId;
import ua.lviv.iot.service.CallHasRescueVehicleService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static ua.lviv.iot.mappers.CallHasRescueVehicleMapper.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/call-has-rescue-vehicle")
public class CallHasRescueVehicleController {

    private final CallHasRescueVehicleService callHasRescueVehicleService;

    @PostMapping
    CallHasRescueVehicleDto createCallHasRescueVehicle(
            final @Valid @RequestBody CallHasRescueVehicleDto callHasRescueVehicleDto) {
        return mapCallHasRescueVehicleToCallHasRescueVehicleDto(
                callHasRescueVehicleService.createCallHasRescueVehicle(callHasRescueVehicleDto));
    }

    @GetMapping
    List<CallHasRescueVehicleDto> getAllCallHasRescueVehicles() {
        return callHasRescueVehicleService.getAllCallHasRescueVehicles().stream()
                .map(CallHasRescueVehicleMapper::mapCallHasRescueVehicleToCallHasRescueVehicleDto)
                .collect(Collectors.toList());
    }


    @PutMapping
    CallHasRescueVehicleDto updateCallHasRescueVehicle(
            final @Valid @RequestBody CallHasRescueVehicleDto callHasRescueVehicleDto) {
        return mapCallHasRescueVehicleToCallHasRescueVehicleDto(
                callHasRescueVehicleService.updateCallHasRescueVehicle(callHasRescueVehicleDto));
    }

    @DeleteMapping
    CallHasRescueVehicleDto deleteCallHasRescueVehicle(final @RequestParam("callId") Integer callId,
                                                       final @RequestParam("rescueVehicleNumber") String number) {
        return mapCallHasRescueVehicleToCallHasRescueVehicleDto(
                callHasRescueVehicleService.deleteCallHasRescueVehicle(new CallRescueVehicleId(callId, number)));
    }
}
