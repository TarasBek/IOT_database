package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.impl.EventDaoImpl;
import ua.lviv.iot.model.Event;

public class EventServiceImpl extends AbstractServiceImpl<Event, Integer> {
    public EventServiceImpl() {
        super(new EventDaoImpl());
    }
}
