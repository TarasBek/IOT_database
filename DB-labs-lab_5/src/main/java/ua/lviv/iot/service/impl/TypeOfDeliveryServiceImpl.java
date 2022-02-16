package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.impl.TypeOfDeliveryDaoImpl;
import ua.lviv.iot.model.TypeOfDelivery;

public class TypeOfDeliveryServiceImpl extends AbstractServiceImpl<TypeOfDelivery, Integer> {
    public TypeOfDeliveryServiceImpl() {
        super(new TypeOfDeliveryDaoImpl());
    }
}
