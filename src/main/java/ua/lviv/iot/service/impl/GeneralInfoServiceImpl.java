package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.impl.GeneralInfoDaoImpl;
import ua.lviv.iot.model.GeneralInfo;

public class GeneralInfoServiceImpl extends AbstractServiceImpl<GeneralInfo, Integer> {
    public GeneralInfoServiceImpl() {
        super(new GeneralInfoDaoImpl());
    }
}
