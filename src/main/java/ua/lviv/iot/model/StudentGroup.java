package ua.lviv.iot.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "student_group")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class StudentGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "entry_year", nullable = false)
    private Integer entryYear;

    @OneToMany(mappedBy = "studentGroup", fetch = FetchType.EAGER)
    private Set<Student> students;

}
