package ua.lviv.iot.controller.impl;

import ua.lviv.iot.model.Ticket;
import ua.lviv.iot.service.impl.TicketServiceImpl;

public class TicketControllerImpl extends AbstractControllerImpl<Ticket, String> {
    public TicketControllerImpl() {
        super(new TicketServiceImpl());
    }
}
