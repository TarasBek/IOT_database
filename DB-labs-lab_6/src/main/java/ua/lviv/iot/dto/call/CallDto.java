package ua.lviv.iot.dto.call;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CallDto {

    private Integer id;

    private String shortDescription;

    private String detailedDescription;

    private LocalDateTime callTime;

    private String reporterId;

    private Integer callAddressId;

    private Set<Integer> rescuers;

    private Set<String> rescueVehicle;
}
