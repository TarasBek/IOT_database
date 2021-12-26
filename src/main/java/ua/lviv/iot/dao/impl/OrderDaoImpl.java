package ua.lviv.iot.dao.impl;

import ua.lviv.iot.model.Order;

public class OrderDaoImpl extends AbstractDaoImpl<Order, Integer> {
    public OrderDaoImpl() {
        super(Order.class);
    }
}
