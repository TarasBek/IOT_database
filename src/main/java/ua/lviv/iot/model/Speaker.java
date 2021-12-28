package ua.lviv.iot.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "speaker")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "firm", nullable = false)
    private String firm;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;


}
