package ua.lviv.iot.dao.impl;

import ua.lviv.iot.model.Products;

public class ProductsDaoImpl extends AbstractDaoImpl<Products, Integer> {
    public ProductsDaoImpl() {
        super(Products.class);
    }
}
