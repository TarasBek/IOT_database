package ua.lviv.iot.model;

import lombok.*;
import ua.lviv.iot.annotations.Column;
import ua.lviv.iot.annotations.PrimaryKey;
import ua.lviv.iot.annotations.Table;

@Table(name = "order")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Order {

    @PrimaryKey
    @Column(name = "id")
    private Integer id;

    @Column(name = "destination_address_id")
    private Integer destinationAddressId;

    @Column(name = "products_id")
    private Integer productsId;
}
