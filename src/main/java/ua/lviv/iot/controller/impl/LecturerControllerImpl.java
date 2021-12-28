package ua.lviv.iot.controller.impl;

import ua.lviv.iot.model.Lecturer;
import ua.lviv.iot.service.impl.LecturerServiceImpl;

public class LecturerControllerImpl extends AbstractControllerImpl<Lecturer, Integer> {
    public LecturerControllerImpl() {
        super(new LecturerServiceImpl());
    }
}
