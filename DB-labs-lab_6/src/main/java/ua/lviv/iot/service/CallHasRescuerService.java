package ua.lviv.iot.service;

import ua.lviv.iot.dto.callhasrescuer.CallHasRescuerDto;
import ua.lviv.iot.model.CallHasRescuer;
import ua.lviv.iot.model.compositekey.CallRescuerId;

import java.util.List;

public interface CallHasRescuerService {

    CallHasRescuer createCallHasRescuer(CallHasRescuerDto callHasRescuerDto);

    List<CallHasRescuer> getAllCallHasRescuers();

    CallHasRescuer updateCallHasRescuer(CallHasRescuerDto callHasRescuerDto);

    CallHasRescuer deleteCallHasRescuer(CallRescuerId id);
}
