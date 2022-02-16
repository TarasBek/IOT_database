package ua.lviv.iot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.CallAddress;

@Repository
public interface CallAddressDao extends JpaRepository<CallAddress, Integer> {
}
