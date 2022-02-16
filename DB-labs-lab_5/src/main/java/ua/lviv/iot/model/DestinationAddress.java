package ua.lviv.iot.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "destination_address")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class DestinationAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "city_village", nullable = false)
    private String cityOrVillage;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "building", nullable = false)
    private String building;

    @Column(name = "flat", nullable = false)
    private String flat;

    @OneToOne(mappedBy = "destinationAddress", fetch = FetchType.EAGER)
    private Order order;
}
