package ua.lviv.iot.dao.impl;

import ua.lviv.iot.model.Student;

public class StudentDaoImpl extends AbstractDaoImpl<Student, String> {
    public StudentDaoImpl() {
        super(Student.class);
    }
}
