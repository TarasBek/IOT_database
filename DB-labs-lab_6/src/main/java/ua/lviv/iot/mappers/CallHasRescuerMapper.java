package ua.lviv.iot.mappers;

import ua.lviv.iot.dto.callhasrescuer.CallHasRescuerDto;
import ua.lviv.iot.model.CallHasRescuer;

public class CallHasRescuerMapper {

    private CallHasRescuerMapper() {
    }

    public static CallHasRescuerDto mapCallHasRescuerToCallHasRescuerDto(
            CallHasRescuer callHasRescuer) {
        return new CallHasRescuerDto(
                callHasRescuer.getId().getCallId(),
                callHasRescuer.getId().getRescuerId(),
                callHasRescuer.getInjury() != null ?
                        callHasRescuer.getInjury().getId()
                        : null
        );
    }
}
