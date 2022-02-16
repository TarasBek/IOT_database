package ua.lviv.iot.dto.rescuer;

import lombok.*;
import ua.lviv.iot.model.enums.Position;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RescuerDto {

    private Integer id;

    private String surname;

    private String name;

    private Position position;

    private boolean isPresent;

    private Set<Integer> calls;
}
