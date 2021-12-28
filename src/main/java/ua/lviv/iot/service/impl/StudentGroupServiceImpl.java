package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.impl.StudentGroupDaoImpl;
import ua.lviv.iot.model.StudentGroup;

public class StudentGroupServiceImpl extends AbstractServiceImpl<StudentGroup, String> {
    public StudentGroupServiceImpl() {
        super(new StudentGroupDaoImpl());
    }
}
