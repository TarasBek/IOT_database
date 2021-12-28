package ua.lviv.iot.controller.impl;

import ua.lviv.iot.model.Response;
import ua.lviv.iot.service.impl.ResponseServiceImpl;

public class ResponseControllerImpl extends AbstractControllerImpl<Response, Integer> {
    public ResponseControllerImpl() {
        super(new ResponseServiceImpl());
    }
}
