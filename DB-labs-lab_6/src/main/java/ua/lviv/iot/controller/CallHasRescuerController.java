package ua.lviv.iot.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dto.callhasrescuer.CallHasRescuerDto;
import ua.lviv.iot.mappers.CallHasRescuerMapper;
import ua.lviv.iot.model.compositekey.CallRescuerId;
import ua.lviv.iot.service.CallHasRescuerService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static ua.lviv.iot.mappers.CallHasRescuerMapper.mapCallHasRescuerToCallHasRescuerDto;

@RestController
@AllArgsConstructor
@RequestMapping("/api/call-has-rescuer")
public class CallHasRescuerController {

    private final CallHasRescuerService callHasRescuerService;

    @PostMapping
    CallHasRescuerDto createCallHasRescuer(final @Valid @RequestBody CallHasRescuerDto callHasRescuerDto) {
        return mapCallHasRescuerToCallHasRescuerDto(callHasRescuerService.createCallHasRescuer(callHasRescuerDto));
    }

    @GetMapping
    List<CallHasRescuerDto> getAllCallHasRescuers() {
        return callHasRescuerService.getAllCallHasRescuers().stream()
                .map(CallHasRescuerMapper::mapCallHasRescuerToCallHasRescuerDto)
                .collect(Collectors.toList());
    }

    @PutMapping
    CallHasRescuerDto updateCallHasRescuer(final @Valid @RequestBody CallHasRescuerDto callHasRescuerDto) {
        return mapCallHasRescuerToCallHasRescuerDto(callHasRescuerService.updateCallHasRescuer(callHasRescuerDto));
    }

    @DeleteMapping
    CallHasRescuerDto deleteCallHasRescuer(final @RequestParam("callId") Integer callId,
                                           final @RequestParam("rescuerId") Integer rescuerId) {
        return mapCallHasRescuerToCallHasRescuerDto(
                callHasRescuerService.deleteCallHasRescuer(new CallRescuerId(callId, rescuerId)));
    }
}
