package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.impl.ResponseDaoImpl;
import ua.lviv.iot.model.Response;

public class ResponseServiceImpl extends AbstractServiceImpl<Response, Integer> {
    public ResponseServiceImpl() {
        super(new ResponseDaoImpl());
    }
}
