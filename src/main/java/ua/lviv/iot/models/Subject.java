package ua.lviv.iot.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "cluster_program_id", nullable = false)
    private ClusterProgram clusterProgram;

    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    private Set<Lecturer> lecturers;

    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    private Set<Speaker> speakers;

    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    private Set<Student> students;
}
