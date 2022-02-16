package ua.lviv.iot.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "order")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "destination_address_id", nullable = false)
    private DestinationAddress destinationAddress;

    @OneToOne
    @JoinColumn(name = "type_of_delivery_id", nullable = false)
    private TypeOfDelivery typeOfDelivery;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Set<Ticket> ticket;
}
