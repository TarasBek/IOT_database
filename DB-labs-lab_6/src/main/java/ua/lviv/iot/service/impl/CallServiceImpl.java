package ua.lviv.iot.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dao.CallDao;
import ua.lviv.iot.dto.call.CreateUpdateCallDto;
import ua.lviv.iot.exception.InvalidDateTimeException;
import ua.lviv.iot.exception.NoDataFoundException;
import ua.lviv.iot.model.Call;
import ua.lviv.iot.model.CallAddress;
import ua.lviv.iot.model.Reporter;
import ua.lviv.iot.service.CallAddressService;
import ua.lviv.iot.service.CallService;
import ua.lviv.iot.service.ReporterService;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CallServiceImpl implements CallService {

    private final CallDao callDao;
    private final ReporterService reporterService;
    private final CallAddressService callAddressService;

    @Override
    public Call createCall(CreateUpdateCallDto createUpdateCallDto) {
        Reporter reporter = reporterService.getReporterById(createUpdateCallDto.getReporterId()); // will throw exception if entity doesn't exist
        CallAddress callAddress = callAddressService.getCallAddressById(createUpdateCallDto.getCallAddressId()); // will throw exception if entity doesn't exist
        LocalDateTime callTime;
        try {
            callTime = LocalDateTime.parse(createUpdateCallDto.getCallTime());
        } catch (DateTimeException e) {
            throw new InvalidDateTimeException("enter valid datetime");
        }
        return callDao.save(new Call(
                createUpdateCallDto.getShortDescription(),
                createUpdateCallDto.getDetailedDescription(),
                callTime,
                reporter,
                callAddress
        ));
    }

    @Override
    public List<Call> getAllCalls() {
        return callDao.findAll();
    }

    @Override
    public Call getCallById(Integer id) {
        return callDao.findById(id)
                .orElseThrow(() -> new NoDataFoundException("Entity with id: " + id + " not found"));
    }

    @Override
    public Call updateCall(CreateUpdateCallDto createUpdateCallDto) {
        getCallById(createUpdateCallDto.getId()); // will throw exception if entity doesn't exist
        Reporter reporter = reporterService.getReporterById(createUpdateCallDto.getReporterId()); // will throw exception if entity doesn't exist
        CallAddress callAddress = callAddressService.getCallAddressById(createUpdateCallDto.getCallAddressId()); // will throw exception if entity doesn't exist
        LocalDateTime callTime;
        try {
            callTime = LocalDateTime.parse(createUpdateCallDto.getCallTime());
        } catch (DateTimeException e) {
            throw new InvalidDateTimeException("enter valid datetime");
        }
        return callDao.save(new Call(
                createUpdateCallDto.getId(),
                createUpdateCallDto.getShortDescription(),
                createUpdateCallDto.getDetailedDescription(),
                callTime,
                reporter,
                callAddress
        ));
    }

    /**
     * Deletes with CascadeType=REMOVE
     */
    @Override
    public Call deleteCallById(Integer id) {
        Call call = getCallById(id); // throws runtime exception if not found by id
        callDao.deleteById(id);
        return call;
    }
}
