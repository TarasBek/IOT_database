package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.impl.UserDaoImpl;
import ua.lviv.iot.model.UserInf;

public class UserInfoServiceImpl extends AbstractServiceImpl<UserInf, Integer> {
    public UserInfoServiceImpl() {
        super(new UserDaoImpl());
    }
}
