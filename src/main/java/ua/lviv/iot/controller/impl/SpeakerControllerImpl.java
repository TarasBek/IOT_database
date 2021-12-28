package ua.lviv.iot.controller.impl;

import ua.lviv.iot.model.Speaker;
import ua.lviv.iot.service.impl.SpeakerServiceImpl;

public class SpeakerControllerImpl extends AbstractControllerImpl<Speaker, Integer> {
    public SpeakerControllerImpl() {
        super(new SpeakerServiceImpl());
    }
}
