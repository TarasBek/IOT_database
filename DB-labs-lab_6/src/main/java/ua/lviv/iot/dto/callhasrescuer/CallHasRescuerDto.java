package ua.lviv.iot.dto.callhasrescuer;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CallHasRescuerDto {

    @NotNull
    private Integer callId;

    @NotNull
    private Integer rescuerId;

    private Integer injuryId;
}
