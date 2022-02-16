package ua.lviv.iot.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "call_address")
@NoArgsConstructor
@Getter
@Setter
public class CallAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(min = 3, max = 100, message = "must be from 3 to 100 chars")
    @NotBlank(message = "is required and must not be blank")
    @Column(name = "region", nullable = false)
    private String region;

    @Size(min = 3, max = 100, message = "must be from 3 to 100 chars")
    @NotBlank(message = "is required and must not be blank")
    @Column(name = "city_or_village", nullable = false)
    private String cityOrVillage;

    @Column(name = "street")
    private String street;

    @Column(name = "building")
    private String building;

    @Column(name = "flat")
    private String flat;

    @Column(name = "place")
    private String place;

    @OneToMany(mappedBy = "callAddress", fetch = FetchType.EAGER)
    private Set<Call> calls;
}
