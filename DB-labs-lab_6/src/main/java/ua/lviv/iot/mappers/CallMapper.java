package ua.lviv.iot.mappers;

import ua.lviv.iot.dto.call.CallDto;
import ua.lviv.iot.model.Call;
import ua.lviv.iot.model.Rescuer;

import java.util.Collections;
import java.util.stream.Collectors;

public class CallMapper {

    private CallMapper() {
    }

    public static CallDto mapCallToCallDto(Call call) {
        return new CallDto(
                call.getId(),
                call.getShortDescription(),
                call.getDetailedDescription(),
                call.getCallTime(),
                call.getReporter().getPhoneNumber(),
                call.getCallAddress().getId(),
                call.getRescuers() != null ?
                        call.getRescuers().stream()
                                .map(callHasRescuer -> callHasRescuer.getRescuer().getId())
                                .collect(Collectors.toSet())
                        : Collections.emptySet(),
                call.getRescueVehicles() != null ?
                        call.getRescueVehicles().stream()
                                .map(callHasRescueVehicle -> callHasRescueVehicle.getRescueVehicle().getNumber())
                                .collect(Collectors.toSet())
                        : Collections.emptySet()
        );
    }
}
