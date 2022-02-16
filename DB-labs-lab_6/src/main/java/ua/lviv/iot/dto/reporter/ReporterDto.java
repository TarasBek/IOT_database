package ua.lviv.iot.dto.reporter;

import lombok.*;
import ua.lviv.iot.model.enums.Position;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReporterDto {

    private String phoneNumber;

    private String surname;

    private String name;

    private Set<Integer> calls;
}
