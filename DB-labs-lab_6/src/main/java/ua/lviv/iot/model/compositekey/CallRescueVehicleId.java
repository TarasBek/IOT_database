package ua.lviv.iot.model.compositekey;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(of = {"callId", "rescueVehicleNumber"})
public class CallRescueVehicleId implements Serializable {

    @Column(name = "call_id", nullable = false)
    private Integer callId;

    @Column(name = "rescue_vehicle_number", nullable = false, length = 8)
    private String rescueVehicleNumber;
}
