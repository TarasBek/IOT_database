package ua.lviv.iot.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity(name = "rescue_vehicle")
@Table(name = "rescue_vehicle")
@NoArgsConstructor
@Getter
@Setter
public class RescueVehicle {

    @Id
    @Column(name = "number", nullable = false)
    @NotBlank(message = "is required and must not be blank")
    @Size(max = 8, message = "must be up to 8 chars")
    private String number;

    @NotBlank(message = "is required and must not be blank")
    @Size(max = 255, message = "must be up to 8 chars")
    @Column(name = "characteristics", nullable = false)
    private String characteristics;

    @OneToMany(mappedBy = "rescueVehicle", fetch = FetchType.EAGER)
    private Set<CallHasRescueVehicle> calls;

}
