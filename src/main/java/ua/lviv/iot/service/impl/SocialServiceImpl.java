package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.impl.SocialDaoImpl;
import ua.lviv.iot.model.Social;

public class SocialServiceImpl extends AbstractServiceImpl<Social, Integer> {
    public SocialServiceImpl() {
        super(new SocialDaoImpl());
    }
}