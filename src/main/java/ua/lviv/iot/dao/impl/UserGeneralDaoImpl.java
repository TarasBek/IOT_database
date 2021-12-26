package ua.lviv.iot.dao.impl;

import ua.lviv.iot.model.User;

public class UserGeneralDaoImpl extends AbstractDaoImpl<User, String> {
    public UserGeneralDaoImpl() {
        super(User.class);
    }
}
