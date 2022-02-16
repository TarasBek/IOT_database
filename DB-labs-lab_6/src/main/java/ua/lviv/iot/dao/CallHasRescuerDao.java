package ua.lviv.iot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.CallHasRescuer;
import ua.lviv.iot.model.compositekey.CallRescuerId;

@Repository
public interface CallHasRescuerDao extends JpaRepository<CallHasRescuer, CallRescuerId> {

    boolean existsCallHasRescuerByInjury_Id(Integer injuryId);

    void deleteCallHasRescuerByInjury_Id(Integer id);
}
