package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.impl.TicketDaoImpl;
import ua.lviv.iot.model.Ticket;

public class TicketServiceImpl extends AbstractServiceImpl<Ticket, String> {
    public TicketServiceImpl() {
        super(new TicketDaoImpl());
    }
}
