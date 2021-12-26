package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.impl.ProductsDaoImpl;
import ua.lviv.iot.model.Products;

public class ProductServiceImpl extends AbstractServiceImpl<Products, Integer> {
    public ProductServiceImpl() {
        super(new ProductsDaoImpl());
    }
}
