package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.impl.DestinationAddressDaoImpl;
import ua.lviv.iot.model.DestinationAddress;

public class DestinationAddressServiceImpl extends AbstractServiceImpl<DestinationAddress, Integer> {
    public DestinationAddressServiceImpl() {
        super(new DestinationAddressDaoImpl());
    }
}
