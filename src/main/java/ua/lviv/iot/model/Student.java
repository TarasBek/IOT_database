package ua.lviv.iot.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "student")
@NoArgsConstructor
@Getter
@Setter
@ToString()
@EqualsAndHashCode(of = "id")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "student_group_id", nullable = false)
    private StudentGroup studentGroup;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private Set<Response> responses;

}
