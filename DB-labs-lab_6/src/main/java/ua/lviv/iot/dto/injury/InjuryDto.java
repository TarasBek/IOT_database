package ua.lviv.iot.dto.injury;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InjuryDto {

    private Integer id;

    @Size(max = 1000, message = "must be up to 1000 chars")
    @NotBlank(message = "is required and must not be blank")
    private String description;

    @Size(max = 255, message = "must be up to 255 chars")
    @NotBlank(message = "is required and must not be blank")
    private String diagnosis;

    @NotNull
    private Integer hospitalId;
}
