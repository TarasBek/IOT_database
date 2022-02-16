package ua.lviv.iot.service;

import ua.lviv.iot.model.CallAddress;

import java.util.List;

public interface CallAddressService {

    CallAddress createCallAddress(CallAddress callAddress);

    List<CallAddress> getAllCallAddresses();

    CallAddress getCallAddressById(Integer id);

    CallAddress updateCallAddress(CallAddress callAddress);

    CallAddress deleteCallAddressById(Integer id);
}
