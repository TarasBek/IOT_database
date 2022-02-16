package ua.lviv.iot.model;

import lombok.*;
import ua.lviv.iot.model.compositekey.CallRescuerId;

import javax.persistence.*;

@Entity
@Table(name = "call_has_rescuer")
@NoArgsConstructor
@Getter
@Setter
public class CallHasRescuer {

    @EmbeddedId
    private CallRescuerId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("callId")
    private Call call;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("rescuerId")
    private Rescuer rescuer;

    @OneToOne
    @JoinColumn(name = "injury_id")
    private Injury injury;

    public CallHasRescuer(CallRescuerId id, Call call, Rescuer rescuer, Injury injury) {
        this.id = id;
        this.call = call;
        this.rescuer = rescuer;
        this.injury = injury;
    }
}
