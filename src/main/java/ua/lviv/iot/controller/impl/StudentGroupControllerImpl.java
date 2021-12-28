package ua.lviv.iot.controller.impl;

import ua.lviv.iot.model.StudentGroup;
import ua.lviv.iot.service.impl.StudentGroupServiceImpl;

public class StudentGroupControllerImpl extends AbstractControllerImpl<StudentGroup, String> {
    public StudentGroupControllerImpl() {
        super(new StudentGroupServiceImpl());
    }
}
