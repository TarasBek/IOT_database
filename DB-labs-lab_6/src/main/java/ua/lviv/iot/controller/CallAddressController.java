package ua.lviv.iot.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dto.calladdress.CallAddressDto;
import ua.lviv.iot.mappers.CallAddressMapper;
import ua.lviv.iot.model.CallAddress;
import ua.lviv.iot.service.CallAddressService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static ua.lviv.iot.mappers.CallAddressMapper.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/call-address")
public class CallAddressController {

    private final CallAddressService callAddressService;

    @PostMapping
    CallAddressDto createCallAddress(final @Valid @RequestBody CallAddress callAddress) {
        return mapCallAddressToCallAddressDto(callAddressService.createCallAddress(callAddress));
    }

    @GetMapping
    List<CallAddressDto> getAllCallAddresses() {
        return callAddressService.getAllCallAddresses().stream()
                .map(CallAddressMapper::mapCallAddressToCallAddressDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    CallAddressDto getCallAddressById(final @PathVariable("id") Integer id) {
        return mapCallAddressToCallAddressDto(callAddressService.getCallAddressById(id));
    }

    @PutMapping
    CallAddressDto updateCallAddress(final @Valid @RequestBody CallAddress callAddress) {
        return mapCallAddressToCallAddressDto(callAddressService.updateCallAddress(callAddress));
    }

    @DeleteMapping("/{id}")
    CallAddressDto deleteCallAddressById(final @PathVariable("id") Integer id) {
        return mapCallAddressToCallAddressDto(callAddressService.deleteCallAddressById(id));
    }
}
