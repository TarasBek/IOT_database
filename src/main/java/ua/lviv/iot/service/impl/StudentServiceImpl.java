package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.impl.StudentDaoImpl;
import ua.lviv.iot.model.Student;

public class StudentServiceImpl extends AbstractServiceImpl<Student, String> {
    public StudentServiceImpl() {
        super(new StudentDaoImpl());
    }
}
