package ua.lviv.iot.controller.impl;

import ua.lviv.iot.model.Student;
import ua.lviv.iot.service.impl.StudentServiceImpl;

public class StudentControllerImpl extends AbstractControllerImpl<Student, String> {
    public StudentControllerImpl() {
        super(new StudentServiceImpl());
    }
}
