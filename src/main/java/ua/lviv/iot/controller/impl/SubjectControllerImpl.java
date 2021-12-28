package ua.lviv.iot.controller.impl;

import ua.lviv.iot.model.Subject;
import ua.lviv.iot.service.impl.SubjectServiceImpl;

public class SubjectControllerImpl extends AbstractControllerImpl<Subject, Integer> {
    public SubjectControllerImpl() {
        super(new SubjectServiceImpl());
    }
}
