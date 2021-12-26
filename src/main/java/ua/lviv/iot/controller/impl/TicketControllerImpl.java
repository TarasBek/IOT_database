package ua.lviv.iot.controller.impl;

import ua.lviv.iot.model.User;
import ua.lviv.iot.service.impl.UserServiceImpl;

public class TicketControllerImpl extends AbstractControllerImpl<User, String> {
    public TicketControllerImpl() {
        super(new UserServiceImpl());
    }
}
