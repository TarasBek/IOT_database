package ua.lviv.iot.mappers;

import ua.lviv.iot.dto.rescuevehicle.RescueVehicleDto;
import ua.lviv.iot.model.RescueVehicle;

import java.util.Collections;
import java.util.stream.Collectors;

public class RescueVehicleMapper {

    private RescueVehicleMapper() {
    }

    public static RescueVehicleDto mapRescueVehicleToRescueVehicleDto(RescueVehicle rescueVehicle) {
        return new RescueVehicleDto(
                rescueVehicle.getNumber(),
                rescueVehicle.getCharacteristics(),
                rescueVehicle.getCalls() != null ?
                        rescueVehicle.getCalls().stream()
                                .map(callHasRescueVehicle -> callHasRescueVehicle.getCall().getId())
                                .collect(Collectors.toSet())
                        : Collections.emptySet()
        );
    }
}
