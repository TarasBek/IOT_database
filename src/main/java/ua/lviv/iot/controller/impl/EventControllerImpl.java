package ua.lviv.iot.controller.impl;

import ua.lviv.iot.model.UserInf;
import ua.lviv.iot.service.impl.UserInfoServiceImpl;

public class EventControllerImpl extends AbstractControllerImpl<UserInf, Integer> {
    public EventControllerImpl() {
        super(new UserInfoServiceImpl());
    }
}
