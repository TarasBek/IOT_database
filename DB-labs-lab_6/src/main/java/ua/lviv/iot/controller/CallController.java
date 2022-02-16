package ua.lviv.iot.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dto.call.CallDto;
import ua.lviv.iot.dto.call.CreateUpdateCallDto;
import ua.lviv.iot.dto.reporter.ReporterDto;
import ua.lviv.iot.mappers.CallMapper;
import ua.lviv.iot.model.Call;
import ua.lviv.iot.service.CallService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static ua.lviv.iot.mappers.CallMapper.mapCallToCallDto;
import static ua.lviv.iot.mappers.ReporterMapper.mapReporterToReporterDto;

@RestController
@AllArgsConstructor
@RequestMapping("/api/call")
public class CallController {

    private final CallService callService;

    @PostMapping
    CallDto createCall(final @Valid @RequestBody CreateUpdateCallDto createUpdateCallDto) {
        return mapCallToCallDto(callService.createCall(createUpdateCallDto));
    }

    @GetMapping
    List<CallDto> getAllCalls() {
        return callService.getAllCalls().stream()
                .map(CallMapper::mapCallToCallDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    CallDto getCallById(final @PathVariable("id") Integer id) {
        return mapCallToCallDto(callService.getCallById(id));
    }

    @PutMapping
    CallDto updateCall(final @Valid @RequestBody CreateUpdateCallDto createUpdateCallDto) {
        return mapCallToCallDto(callService.updateCall(createUpdateCallDto));
    }

    @DeleteMapping("/{id}")
    CallDto deleteCallById(final @PathVariable("id") Integer id) {
        return mapCallToCallDto(callService.deleteCallById(id));
    }
}
