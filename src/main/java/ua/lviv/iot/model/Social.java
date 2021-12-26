package ua.lviv.iot.model;

import lombok.*;
import ua.lviv.iot.annotations.Column;
import ua.lviv.iot.annotations.PrimaryKey;
import ua.lviv.iot.annotations.Table;

@Table(name = "social_id")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Social {

    @PrimaryKey
    @Column(name = "id")
    private Integer id;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "instagram")
    private String instagram;
    
    @Column(name = "twitter")
    private String twitter;
}
