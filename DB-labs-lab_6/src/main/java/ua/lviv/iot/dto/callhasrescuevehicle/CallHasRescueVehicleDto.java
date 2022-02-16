package ua.lviv.iot.dto.callhasrescuevehicle;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CallHasRescueVehicleDto {

    @NotNull
    private Integer callId;

    @Size(max = 8, message = "must be up to 8 chars")
    @NotBlank(message = "is required and must not be blank")
    private String rescueVehicleId;

    @Pattern(regexp = "(\\d{4})-(\\d{2})-(\\d{2})T(\\d{2}):(\\d{2}):(\\d{2})\\.(\\d{2})",
            message = "time must match pattern: yyyy-mm-ddThh:mm:ss.xx")
    private String departureTime;

    @Pattern(regexp = "(\\d{4})-(\\d{2})-(\\d{2})T(\\d{2}):(\\d{2}):(\\d{2})\\.(\\d{2})",
            message = "time must match pattern: yyyy-mm-ddThh:mm:ss.xx")
    private String returnTime;
}
