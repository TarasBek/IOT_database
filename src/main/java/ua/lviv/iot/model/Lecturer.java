package ua.lviv.iot.model;

import lombok.*;
import ua.lviv.iot.annotations.Column;
import ua.lviv.iot.annotations.PrimaryKey;
import ua.lviv.iot.annotations.Table;

@Table(name = "lecturer")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Lecturer {

    @PrimaryKey
    @Column(name = "id")
    private Integer id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "subject_id")
    private Integer subjectID;

}
