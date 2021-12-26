package ua.lviv.iot.model;

import lombok.*;
import ua.lviv.iot.annotations.Column;
import ua.lviv.iot.annotations.PrimaryKey;
import ua.lviv.iot.annotations.Table;

import java.time.LocalDateTime;

@Table(name = "transaction")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Transaction {

    @PrimaryKey
    @Column(name = "id")
    private Integer id;

    @Column(name = "data_of_transaction")
    private LocalDateTime dataOfTransaction;

    @Column(name = "sum")
    private Integer sum;

    @Column(name = "discount_in_percent")
    private Integer discountInPercent;

    @Column(name = "status_of_transaction")
    private String statusOfTransaction;
}
