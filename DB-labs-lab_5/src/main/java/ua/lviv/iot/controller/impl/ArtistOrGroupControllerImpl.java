package ua.lviv.iot.controller.impl;

import ua.lviv.iot.model.ArtistOrGroup;
import ua.lviv.iot.service.impl.ArtistOrGroupServiceImpl;

public class ArtistOrGroupControllerImpl extends AbstractControllerImpl<ArtistOrGroup, Integer> {
    public ArtistOrGroupControllerImpl() {
        super(new ArtistOrGroupServiceImpl());
    }
}
