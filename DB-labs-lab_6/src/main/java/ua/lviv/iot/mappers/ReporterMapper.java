package ua.lviv.iot.mappers;

import ua.lviv.iot.dto.reporter.ReporterDto;
import ua.lviv.iot.model.Call;
import ua.lviv.iot.model.Reporter;

import java.util.Collections;
import java.util.stream.Collectors;

public class ReporterMapper {

    private ReporterMapper() {
    }

    public static ReporterDto mapReporterToReporterDto(Reporter reporter) {
        return new ReporterDto(
                reporter.getPhoneNumber(),
                reporter.getSurname(),
                reporter.getName(),
                reporter.getCalls() != null ?
                        reporter.getCalls().stream()
                                .map(Call::getId)
                                .collect(Collectors.toSet())
                        : Collections.emptySet()
        );
    }
}
