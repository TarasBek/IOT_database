package ua.lviv.iot.model;

import lombok.*;
import ua.lviv.iot.annotations.Column;
import ua.lviv.iot.annotations.PrimaryKey;
import ua.lviv.iot.annotations.Table;

@Table(name = "response")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Response {

    @PrimaryKey
    @Column(name = "id")
    private Integer id;

    @Column(name = "data")
    private String data;

    @Column(name = "text")
    private String text;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "student_id")
    private Integer student_id;


}
