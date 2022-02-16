package ua.lviv.iot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.Injury;

import java.util.List;

@Repository
public interface InjuryDao extends JpaRepository<Injury, Integer> {

    @Query("select injury.id from Injury injury where injury.hospital.id = :id")
    List<Integer> findInjury_IdsByHospital_id(Integer id);
}
