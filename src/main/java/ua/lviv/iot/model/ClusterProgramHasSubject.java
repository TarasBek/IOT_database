package ua.lviv.iot.model;

import lombok.*;
import ua.lviv.iot.model.composite_key.ClusterProgramSubjectId;

import javax.persistence.*;

@Entity
@Table(name = "cluster_program_has_subject")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class ClusterProgramHasSubject {

    @EmbeddedId
    private ClusterProgramSubjectId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("clusterProgramId")
    private ClusterProgram clusterProgram;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("subjectId")
    private Subject subject;


}
