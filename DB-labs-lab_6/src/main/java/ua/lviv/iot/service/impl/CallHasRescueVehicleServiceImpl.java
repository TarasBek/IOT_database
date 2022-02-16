package ua.lviv.iot.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dao.CallHasRescueVehicleDao;
import ua.lviv.iot.dto.callhasrescuevehicle.CallHasRescueVehicleDto;
import ua.lviv.iot.exception.EntityAlreadyExistsException;
import ua.lviv.iot.exception.NoDataFoundException;
import ua.lviv.iot.model.Call;
import ua.lviv.iot.model.CallHasRescueVehicle;
import ua.lviv.iot.model.RescueVehicle;
import ua.lviv.iot.model.compositekey.CallRescueVehicleId;
import ua.lviv.iot.service.CallHasRescueVehicleService;
import ua.lviv.iot.service.CallService;
import ua.lviv.iot.service.RescueVehicleService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CallHasRescueVehicleServiceImpl implements CallHasRescueVehicleService {

    private final CallHasRescueVehicleDao callHasRescueVehicleDao;
    private final CallService callService;
    private final RescueVehicleService rescueVehicleService;

    @Override
    public CallHasRescueVehicle createCallHasRescueVehicle(CallHasRescueVehicleDto callHasRescueVehicleDto) {
        Integer callId = callHasRescueVehicleDto.getCallId();
        String rescueVehicleId = callHasRescueVehicleDto.getRescueVehicleId();
        CallRescueVehicleId id = new CallRescueVehicleId(callId, rescueVehicleId);
        if (callHasRescueVehicleDao.findById(id).isPresent()) {
            throw new EntityAlreadyExistsException("Entity with id: " + id + " already exists");
        }
        Call call = callService.getCallById(callId); // will throw exception if entity doesn't exist
        RescueVehicle rescueVehicle = rescueVehicleService.getRescueVehicleById(rescueVehicleId); // will throw exception if entity doesn't exist
        return callHasRescueVehicleDao.save(new CallHasRescueVehicle(
                id,
                call,
                rescueVehicle,
                LocalDateTime.parse(callHasRescueVehicleDto.getDepartureTime()),
                LocalDateTime.parse(callHasRescueVehicleDto.getReturnTime())
        ));
    }

    @Override
    public List<CallHasRescueVehicle> getAllCallHasRescueVehicles() {
        return callHasRescueVehicleDao.findAll();
    }

    @Override
    public CallHasRescueVehicle updateCallHasRescueVehicle(CallHasRescueVehicleDto callHasRescueVehicleDto) {
        Integer callId = callHasRescueVehicleDto.getCallId();
        String rescueVehicleId = callHasRescueVehicleDto.getRescueVehicleId();
        CallRescueVehicleId id = new CallRescueVehicleId(callId, rescueVehicleId);
        callHasRescueVehicleDao.findById(id)
                .orElseThrow(() -> new NoDataFoundException("Entity with id: " + id + " not found")); // throws exception if entity doesn't exist
        Call call = callService.getCallById(callId); // will throw exception if entity doesn't exist
        RescueVehicle rescueVehicle = rescueVehicleService.getRescueVehicleById(rescueVehicleId); // will throw exception if entity doesn't exist
        return callHasRescueVehicleDao.save(new CallHasRescueVehicle(
                id,
                call,
                rescueVehicle,
                LocalDateTime.parse(callHasRescueVehicleDto.getDepartureTime()),
                LocalDateTime.parse(callHasRescueVehicleDto.getReturnTime())
        ));
    }

    /**
     * Just deletes, no foreign key associations
     */
    @Override
    public CallHasRescueVehicle deleteCallHasRescueVehicle(CallRescueVehicleId id) {
        CallHasRescueVehicle callHasRescueVehicle = callHasRescueVehicleDao.findById(id)
                .orElseThrow(() -> new NoDataFoundException("Entity with id: " + id + " not found")); // throws exception if entity doesn't exist
        callHasRescueVehicleDao.deleteById(id);
        return callHasRescueVehicle;
    }
}
