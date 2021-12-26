package ua.lviv.iot.controller.impl;

import ua.lviv.iot.model.Social;
import ua.lviv.iot.service.impl.SocialServiceImpl;

public class TypeOfEventControllerImpl extends AbstractControllerImpl<Social, Integer> {
    public TypeOfEventControllerImpl() {
        super(new SocialServiceImpl());
    }
}