package ua.lviv.iot.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "type_of_delivery")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class TypeOfDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "delivery_description", nullable = false)
    private String deliveryDescription;

    @OneToOne(mappedBy = "typeOfDelivery", fetch = FetchType.EAGER)
    private Order order;

}
