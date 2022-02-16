package ua.lviv.iot.dao.impl;

import ua.lviv.iot.model.Event;

public class EventDaoImpl extends AbstractDaoImpl<Event, Integer> {

    public EventDaoImpl() {
        super(Event.class);
    }
}
