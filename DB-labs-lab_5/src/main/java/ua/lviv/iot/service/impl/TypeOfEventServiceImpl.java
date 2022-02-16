package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.impl.TypeOfEventDaoImpl;
import ua.lviv.iot.model.TypeOfEvent;

public class TypeOfEventServiceImpl extends AbstractServiceImpl<TypeOfEvent, Integer> {
    public TypeOfEventServiceImpl() {
        super(new TypeOfEventDaoImpl());
    }
}
