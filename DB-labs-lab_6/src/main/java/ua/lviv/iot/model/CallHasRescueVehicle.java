package ua.lviv.iot.model;

import lombok.*;
import ua.lviv.iot.dao.CallHasRescueVehicleDao;
import ua.lviv.iot.dto.callhasrescuevehicle.CallHasRescueVehicleDto;
import ua.lviv.iot.model.compositekey.CallRescueVehicleId;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "call_has_rescue_vehicle")
@NoArgsConstructor
@Getter
@Setter
public class CallHasRescueVehicle {

    @EmbeddedId
    private CallRescueVehicleId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("callId")
    private Call call;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("rescueVehicleNumber")
    private RescueVehicle rescueVehicle;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "return_time", nullable = false)
    private LocalDateTime returnTime;

    public CallHasRescueVehicle(CallRescueVehicleId id, Call call, RescueVehicle rescueVehicle,
                                LocalDateTime departureTime, LocalDateTime returnTime) {
        this.id = id;
        this.call = call;
        this.rescueVehicle = rescueVehicle;
        this.departureTime = departureTime;
        this.returnTime = returnTime;
    }
}
