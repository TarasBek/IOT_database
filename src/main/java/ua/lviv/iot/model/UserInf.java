package ua.lviv.iot.model;

import lombok.*;
import ua.lviv.iot.annotations.Column;
import ua.lviv.iot.annotations.PrimaryKey;
import ua.lviv.iot.annotations.Table;

import java.time.LocalDateTime;

@Table(name = "user_info")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class UserInf {

    @PrimaryKey
    @Column(name = "id")
    private Integer id;

    @Column(name = "general_info_id")
    private Integer generalInfoId;

    @Column(name = "social_info_idsocial_info")
    private Integer socialInfoId;
}
