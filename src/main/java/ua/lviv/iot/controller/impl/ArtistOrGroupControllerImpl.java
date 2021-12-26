package ua.lviv.iot.controller.impl;

import ua.lviv.iot.model.GeneralInfo;
import ua.lviv.iot.service.impl.GeneralInfoServiceImpl;

public class ArtistOrGroupControllerImpl extends AbstractControllerImpl<GeneralInfo, Integer> {
    public ArtistOrGroupControllerImpl() {
        super(new GeneralInfoServiceImpl());
    }
}
