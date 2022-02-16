package ua.lviv.iot.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
import ua.lviv.iot.dao.CallAddressDao;
import ua.lviv.iot.exception.ForeignKeyConstraintException;
import ua.lviv.iot.exception.NoDataFoundException;
import ua.lviv.iot.model.CallAddress;
import ua.lviv.iot.service.CallAddressService;

import java.util.List;

@Service
@AllArgsConstructor
public class CallAddressServiceImpl implements CallAddressService {

    private final CallAddressDao callAddressDao;

    @Override
    public CallAddress createCallAddress(CallAddress callAddress) {
        return callAddressDao.save(callAddress);
    }

    @Override
    public List<CallAddress> getAllCallAddresses() {
        return callAddressDao.findAll();
    }

    @Override
    public CallAddress getCallAddressById(Integer id) {
        return callAddressDao.findById(id)
                .orElseThrow(() -> new NoDataFoundException("Entity with id: " + id + " not found"));
    }

    @Override
    public CallAddress updateCallAddress(CallAddress callAddress) {
        getCallAddressById(callAddress.getId()); // throws exception if entity doesn't exist
        return callAddressDao.save(callAddress);
    }

    /**
     * Deletes only if there is no foreign key associations
     */
    @Override
    public CallAddress deleteCallAddressById(Integer id) {
        CallAddress oldCallAddress = getCallAddressById(id); // throws runtime exception if not found by id
        try {
            callAddressDao.deleteById(id);
        } catch (Exception e) {
            throw new ForeignKeyConstraintException("Unable to delete this entity because its id is used in child table");
        }
        return oldCallAddress;
    }
}
