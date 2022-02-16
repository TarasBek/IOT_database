package ua.lviv.iot.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dto.reporter.ReporterDto;
import ua.lviv.iot.mappers.ReporterMapper;
import ua.lviv.iot.model.Reporter;
import ua.lviv.iot.service.ReporterService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static ua.lviv.iot.mappers.ReporterMapper.mapReporterToReporterDto;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reporter")
public class ReporterController {

    private final ReporterService reporterService;

    @PostMapping
    ReporterDto createReporter(final @Valid @RequestBody Reporter reporter) {
        return mapReporterToReporterDto(reporterService.createReporter(reporter));
    }

    @GetMapping
    List<ReporterDto> getAllReporters() {
        return reporterService.getAllReporters().stream()
                .map(ReporterMapper::mapReporterToReporterDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    ReporterDto getReporterById(final @PathVariable("id") String phoneNumber) {
        return mapReporterToReporterDto(reporterService.getReporterById(phoneNumber));
    }

    @PutMapping
    ReporterDto updateReporter(final @Valid @RequestBody Reporter reporter) {
        return mapReporterToReporterDto(reporterService.updateReporter(reporter));
    }

    @DeleteMapping("/{reporterPhoneNumber}")
    ReporterDto deleteReporterById(final @PathVariable("reporterPhoneNumber") String id) {
        return mapReporterToReporterDto(reporterService.deleteReporterById(id));
    }
}
