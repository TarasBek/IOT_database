package ua.lviv.iot.dto.rescuevehicle;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RescueVehicleDto {

    private String number;

    private String characteristics;

    private Set<Integer> calls;
}
