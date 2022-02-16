package ua.lviv.iot.controller.impl;

import ua.lviv.iot.model.TypeOfDelivery;
import ua.lviv.iot.service.impl.TypeOfDeliveryServiceImpl;

public class TypeOfDeliveryControllerImpl extends AbstractControllerImpl<TypeOfDelivery, Integer> {
    public TypeOfDeliveryControllerImpl() {
        super(new TypeOfDeliveryServiceImpl());
    }
}
