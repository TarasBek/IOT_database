package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.impl.SpeakerDaoImpl;
import ua.lviv.iot.model.Speaker;

public class SpeakerServiceImpl extends AbstractServiceImpl<Speaker, Integer> {
    public SpeakerServiceImpl() {
        super(new SpeakerDaoImpl());
    }
}
