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
@EqualsAndHashCode(of = {"callId", "rescuerId"})
public class CallRescuerId implements Serializable {

    @Column(name = "call_id", nullable = false)
    private Integer callId;

    @Column(name = "rescuer_id", nullable = false)
    private Integer rescuerId;
}
