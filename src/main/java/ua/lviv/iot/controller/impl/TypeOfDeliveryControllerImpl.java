package ua.lviv.iot.controller.impl;

import ua.lviv.iot.model.Products;
import ua.lviv.iot.service.impl.ProductServiceImpl;

public class TypeOfDeliveryControllerImpl extends AbstractControllerImpl<Products, Integer> {
    public TypeOfDeliveryControllerImpl() {
        super(new ProductServiceImpl());
    }
}
