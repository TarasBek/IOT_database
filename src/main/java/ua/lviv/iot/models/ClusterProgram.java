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
@Table(name = "cluster_program")
public class ClusterProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "time_of_event")
    private String timeOfEvent;

    @OneToMany(mappedBy = "clusterProgram", fetch = FetchType.EAGER)
    private Set<Subject> subjects;
}
