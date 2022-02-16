package ua.lviv.iot.mappers;

import ua.lviv.iot.dto.rescuer.RescuerDto;
import ua.lviv.iot.model.Rescuer;

import java.util.Collections;
import java.util.stream.Collectors;

public class RescuerMapper {

    private RescuerMapper() {
    }

    public static RescuerDto mapRescuerToRescuerDto(Rescuer rescuer) {
        return new RescuerDto(
                rescuer.getId(),
                rescuer.getSurname(),
                rescuer.getName(),
                rescuer.getPosition(),
                rescuer.isPresent(),
                rescuer.getCalls() != null ?
                        rescuer.getCalls().stream()
                                .map(callHasRescuer -> callHasRescuer.getCall().getId())
                                .collect(Collectors.toSet())
                        : Collections.emptySet()
        );
    }
}
