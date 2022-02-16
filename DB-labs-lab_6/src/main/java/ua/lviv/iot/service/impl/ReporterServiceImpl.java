package ua.lviv.iot.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dao.ReporterDao;
import ua.lviv.iot.exception.EntityAlreadyExistsException;
import ua.lviv.iot.exception.NoDataFoundException;
import ua.lviv.iot.model.Reporter;
import ua.lviv.iot.service.ReporterService;

import java.util.List;

@Service
@AllArgsConstructor
public class ReporterServiceImpl implements ReporterService {

    private final ReporterDao reporterDao;

    @Override
    public Reporter createReporter(Reporter reporter) {
        String id = reporter.getPhoneNumber();
        if (reporterDao.existsById(id)) {
            throw new EntityAlreadyExistsException("Entity with id: " + id + " already exists");
        }
        return reporterDao.save(reporter);
    }

    @Override
    public List<Reporter> getAllReporters() {
        return reporterDao.findAll();
    }

    @Override
    public Reporter getReporterById(String phoneNumber) {
        return reporterDao.findById(phoneNumber)
                .orElseThrow(() -> new NoDataFoundException("Entity with id: " + phoneNumber + " not found"));
    }

    @Override
    public Reporter updateReporter(Reporter reporter) {
        getReporterById(reporter.getPhoneNumber());  // will throw exception if entity doesn't exist
        return reporterDao.save(reporter);
    }

    /**
     * Deletes with CascadeType=REMOVE
     */
    @Override
    public Reporter deleteReporterById(String id) {
        Reporter reporter = getReporterById(id); // throws runtime exception if not found by id
        reporterDao.deleteById(id);
        return reporter;
    }
}
