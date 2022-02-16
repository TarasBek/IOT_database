package ua.lviv.iot.service;

import ua.lviv.iot.model.Reporter;

import java.util.List;

public interface ReporterService {

    Reporter createReporter(Reporter reporter);

    List<Reporter> getAllReporters();

    Reporter getReporterById(String phoneNumber);

    Reporter updateReporter(Reporter reporter);

    Reporter deleteReporterById(String id);
}
