package ua.lviv.iot.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "cluster_program")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class ClusterProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "time_of_event")
    private String timeOfEvent;

    @OneToMany(mappedBy = "clusterProgram", fetch = FetchType.EAGER)
    private Set<ClusterProgramHasSubject> subjects;


}
