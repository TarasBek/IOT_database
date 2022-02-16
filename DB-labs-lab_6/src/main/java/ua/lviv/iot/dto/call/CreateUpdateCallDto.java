package ua.lviv.iot.dto.call;

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
public class CreateUpdateCallDto {

    private Integer id;

    @Size(max = 255, message = "must be up to 255 chars")
    @NotBlank(message = "is required and must not be blank")
    private String shortDescription;

    @Size(max = 3000, message = "must be up to 3000 chars")
    private String detailedDescription;

    @Pattern(regexp = "(\\d{4})-(\\d{2})-(\\d{2})T(\\d{2}):(\\d{2}):(\\d{2})\\.(\\d{2})",
            message = "time must match pattern: yyyy-mm-ddThh:mm:ss.xx")
    private String callTime;

    @Size(max = 12, message = "must be up to 12 chars")
    @NotBlank(message = "is required and must not be blank")
    private String reporterId;

    @NotNull
    private Integer callAddressId;
}
