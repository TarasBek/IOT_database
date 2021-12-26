package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.impl.UserGeneralDaoImpl;
import ua.lviv.iot.model.User;

public class UserServiceImpl extends AbstractServiceImpl<User, String> {
    public UserServiceImpl() {
        super(new UserGeneralDaoImpl());
    }
}
