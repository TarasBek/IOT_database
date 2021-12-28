package ua.lviv.iot.model;

import lombok.*;
import ua.lviv.iot.annotations.Column;
import ua.lviv.iot.annotations.PrimaryKey;
import ua.lviv.iot.annotations.Table;

@Table(name = "student")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Student {

    @PrimaryKey
    @Column(name = "id")
    private Integer id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "student_group_id")
    private Integer studentGroupId;


}
