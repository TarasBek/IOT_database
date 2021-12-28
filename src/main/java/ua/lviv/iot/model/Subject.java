package ua.lviv.iot.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subject")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    private Set<Lecturer> lecturers;

    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    private Set<Speaker> speakers;

    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    private Set<StudentHasSubject> students;



}
