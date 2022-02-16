package ua.lviv.iot.mappers;

import ua.lviv.iot.dto.calladdress.CallAddressDto;
import ua.lviv.iot.model.Call;
import ua.lviv.iot.model.CallAddress;

import java.util.Collections;
import java.util.stream.Collectors;

public class CallAddressMapper {

    private CallAddressMapper() {
    }

    public static CallAddressDto mapCallAddressToCallAddressDto(CallAddress callAddress) {
        return new CallAddressDto(
                callAddress.getId(),
                callAddress.getRegion(),
                callAddress.getCityOrVillage(),
                callAddress.getStreet(),
                callAddress.getBuilding(),
                callAddress.getFlat(),
                callAddress.getPlace(),
                callAddress.getCalls() != null ?
                        callAddress.getCalls().stream()
                                .map(Call::getId)
                                .collect(Collectors.toSet())
                        : Collections.emptySet()
        );
    }
}
