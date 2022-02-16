package ua.lviv.iot.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dao.CallHasRescuerDao;
import ua.lviv.iot.dto.callhasrescuer.CallHasRescuerDto;
import ua.lviv.iot.exception.EntityAlreadyExistsException;
import ua.lviv.iot.exception.NoDataFoundException;
import ua.lviv.iot.model.Call;
import ua.lviv.iot.model.CallHasRescuer;
import ua.lviv.iot.model.Rescuer;
import ua.lviv.iot.model.compositekey.CallRescuerId;
import ua.lviv.iot.service.CallHasRescuerService;
import ua.lviv.iot.service.CallService;
import ua.lviv.iot.service.InjuryService;
import ua.lviv.iot.service.RescuerService;

import java.util.List;

@Service
@AllArgsConstructor
public class CallHasRescuerServiceImpl implements CallHasRescuerService {

    private final CallHasRescuerDao callHasRescuerDao;
    private final CallService callService;
    private final RescuerService rescuerService;
    private final InjuryService injuryService;

    @Override
    public CallHasRescuer createCallHasRescuer(CallHasRescuerDto callHasRescuerDto) {
        Integer callId = callHasRescuerDto.getCallId();
        Integer rescuerId = callHasRescuerDto.getRescuerId();
        Integer injuryId = callHasRescuerDto.getInjuryId();
        CallRescuerId id = new CallRescuerId(callId, rescuerId);
        if (callHasRescuerDao.existsById(id)) {
            throw new EntityAlreadyExistsException("Entity with id: " + id + " already exists");
        }
        if (injuryId != null && callHasRescuerDao.existsCallHasRescuerByInjury_Id(injuryId)) {
            throw new EntityAlreadyExistsException("Injury with id: " + injuryId + " already exists. Create new one");
        }
        Call call = callService.getCallById(callId); // will throw exception if entity doesn't exist
        Rescuer rescuer = rescuerService.getRescuerById(rescuerId); // will throw exception if entity doesn't exist
        return callHasRescuerDao.save(new CallHasRescuer(
                id,
                call,
                rescuer,
                injuryId != null ?
                        injuryService.getInjuryById(injuryId)
                        : null
        ));
    }

    @Override
    public List<CallHasRescuer> getAllCallHasRescuers() {
        return callHasRescuerDao.findAll();
    }

    @Override
    public CallHasRescuer updateCallHasRescuer(CallHasRescuerDto callHasRescuerDto) {
        Integer callId = callHasRescuerDto.getCallId();
        Integer rescuerId = callHasRescuerDto.getRescuerId();
        Integer injuryId = callHasRescuerDto.getInjuryId();
        CallRescuerId id = new CallRescuerId(callId, rescuerId);
        if (!callHasRescuerDao.existsById(id)) {
            throw new NoDataFoundException("Entity with id: " + id + " not found");
        }
        if (injuryId != null && callHasRescuerDao.existsCallHasRescuerByInjury_Id(injuryId)) {
            throw new EntityAlreadyExistsException("Injury with id: " + injuryId + " already exists. Create new one");
        }
        Call call = callService.getCallById(callId); // will throw exception if entity doesn't exist
        Rescuer rescuer = rescuerService.getRescuerById(rescuerId); // will throw exception if entity doesn't exist
        return callHasRescuerDao.save(new CallHasRescuer(
                id,
                call,
                rescuer,
                injuryId != null ?
                        injuryService.getInjuryById(injuryId)
                        : null
        ));
    }

    /**
     * Just deletes, no foreign key associations
     */
    @Override
    public CallHasRescuer deleteCallHasRescuer(CallRescuerId id) {
        CallHasRescuer callHasRescuer = callHasRescuerDao.findById(id)
                .orElseThrow(() -> new NoDataFoundException("Entity with id: " + id + " not found")); // throws exception if entity doesn't exist
        callHasRescuerDao.deleteById(id);
        return callHasRescuer;
    }
}
