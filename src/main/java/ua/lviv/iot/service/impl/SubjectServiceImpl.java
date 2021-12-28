package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.impl.SubjectDaoImpl;
import ua.lviv.iot.model.Subject;

public class SubjectServiceImpl extends AbstractServiceImpl<Subject, Integer> {
    public SubjectServiceImpl() {
        super(new SubjectDaoImpl());
    }
}
