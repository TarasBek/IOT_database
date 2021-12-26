package ua.lviv.iot.controller.impl;

import ua.lviv.iot.model.DestinationAddress;
import ua.lviv.iot.service.impl.DestinationAddressServiceImpl;

public class DestinationAddressControllerImpl extends AbstractControllerImpl<DestinationAddress, Integer> {
    public DestinationAddressControllerImpl() {
        super(new DestinationAddressServiceImpl());
    }
}
