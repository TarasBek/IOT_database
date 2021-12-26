package ua.lviv.iot.model;

import lombok.*;
import ua.lviv.iot.annotations.Column;
import ua.lviv.iot.annotations.PrimaryKey;
import ua.lviv.iot.annotations.Table;

@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class User {

    @PrimaryKey
    @Column(name = "id")
    private Integer id;

    @Column(name = "order_idorder")
    private Integer orderId;

    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "user_info_id")
    private Integer userInfoId;
}
