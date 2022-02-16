package ua.lviv.iot.controller.impl;

import ua.lviv.iot.model.TypeOfEvent;
import ua.lviv.iot.service.impl.TypeOfEventServiceImpl;

public class TypeOfEventControllerImpl extends AbstractControllerImpl<TypeOfEvent, Integer> {
    public TypeOfEventControllerImpl() {
        super(new TypeOfEventServiceImpl());
    }
}