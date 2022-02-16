package ua.lviv.iot.service;

import ua.lviv.iot.model.RescueVehicle;

import java.util.List;

public interface RescueVehicleService {

    RescueVehicle createRescueVehicle(RescueVehicle rescueVehicle);

    List<RescueVehicle> getAllRescueVehicles();

    RescueVehicle getRescueVehicleById(String number);

    RescueVehicle updateRescueVehicle(RescueVehicle rescueVehicle);

    RescueVehicle deleteRescueVehicleById(String number);
}
