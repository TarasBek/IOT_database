package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.impl.ArtistOrGroupDaoImpl;
import ua.lviv.iot.model.ArtistOrGroup;

public class ArtistOrGroupServiceImpl extends AbstractServiceImpl<ArtistOrGroup, Integer> {
    public ArtistOrGroupServiceImpl() {
        super(new ArtistOrGroupDaoImpl());
    }
}
