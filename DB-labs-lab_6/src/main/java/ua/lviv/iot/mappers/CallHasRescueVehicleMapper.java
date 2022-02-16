package ua.lviv.iot.mappers;

import ua.lviv.iot.dto.callhasrescuevehicle.CallHasRescueVehicleDto;
import ua.lviv.iot.model.CallHasRescueVehicle;

public class CallHasRescueVehicleMapper {

    private CallHasRescueVehicleMapper() {
    }

    public static CallHasRescueVehicleDto mapCallHasRescueVehicleToCallHasRescueVehicleDto(
            CallHasRescueVehicle callHasRescueVehicle) {
        return new CallHasRescueVehicleDto(
                callHasRescueVehicle.getId().getCallId(),
                callHasRescueVehicle.getId().getRescueVehicleNumber(),
                callHasRescueVehicle.getDepartureTime().toString(),
                callHasRescueVehicle.getReturnTime().toString()
        );
    }
}
