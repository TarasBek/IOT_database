package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.impl.LecturerDaoImpl;
import ua.lviv.iot.model.Lecturer;

public class LecturerServiceImpl extends AbstractServiceImpl<Lecturer, Integer> {
    public LecturerServiceImpl() {
        super(new LecturerDaoImpl());
    }
}
