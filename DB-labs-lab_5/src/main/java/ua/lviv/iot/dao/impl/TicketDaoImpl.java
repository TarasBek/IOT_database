package ua.lviv.iot.dao.impl;

import ua.lviv.iot.model.Ticket;

public class TicketDaoImpl extends AbstractDaoImpl<Ticket, String> {
    public TicketDaoImpl() {
        super(Ticket.class);
    }
}
