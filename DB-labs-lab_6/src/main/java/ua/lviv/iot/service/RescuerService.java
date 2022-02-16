package ua.lviv.iot.service;

import ua.lviv.iot.model.Rescuer;

import java.util.List;

public interface RescuerService {

    Rescuer createRescuer(Rescuer rescuer);

    List<Rescuer> getAllRescuers();

    Rescuer getRescuerById(Integer id);

    Rescuer updateRescuer(Rescuer rescuer);

    Rescuer deleteRescuerById(Integer id);
}
