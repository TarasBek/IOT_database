package ua.lviv.iot.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "transaction")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "data_of_transaction", nullable = false)
    private LocalDateTime dataOfTransaction;

    @Column(name = "sum", nullable = false)
    private Integer sum;

    @Column(name = "discount_in_percent", nullable = false)
    private Integer discountInPercent;

    @Column(name = "status_of_transaction", nullable = false)
    private String statusOfTransaction;

    @OneToOne(mappedBy = "transaction", fetch = FetchType.EAGER)
    private Ticket ticket;

}
