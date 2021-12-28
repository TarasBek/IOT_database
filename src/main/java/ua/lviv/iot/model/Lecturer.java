package ua.lviv.iot.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "lecturer")
@NoArgsConstructor
@Getter
@Setter

@EqualsAndHashCode(of = "id")
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "surname", nullable = false)
    private String surname;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

}
