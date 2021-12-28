package ua.lviv.iot.model;

import lombok.*;
import ua.lviv.iot.annotations.Column;
import ua.lviv.iot.annotations.PrimaryKey;
import ua.lviv.iot.annotations.Table;

@Table(name = "subject")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Subject {

    @PrimaryKey
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

}
