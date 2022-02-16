package ua.lviv.iot.service;

import ua.lviv.iot.dto.callhasrescuevehicle.CallHasRescueVehicleDto;
import ua.lviv.iot.model.CallHasRescueVehicle;
import ua.lviv.iot.model.compositekey.CallRescueVehicleId;

import java.util.List;

public interface CallHasRescueVehicleService {

    CallHasRescueVehicle createCallHasRescueVehicle(CallHasRescueVehicleDto callHasRescueVehicleDto);

    List<CallHasRescueVehicle> getAllCallHasRescueVehicles();

    CallHasRescueVehicle updateCallHasRescueVehicle(CallHasRescueVehicleDto callHasRescueVehicleDto);

    CallHasRescueVehicle deleteCallHasRescueVehicle(CallRescueVehicleId id);
}
