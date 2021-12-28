package ua.lviv.iot.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "speaker")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "firm", nullable = false)
    private String firm;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;


}
