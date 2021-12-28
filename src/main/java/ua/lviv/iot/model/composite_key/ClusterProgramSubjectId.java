package ua.lviv.iot.model.composite_key;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(of = {"clusterProgramId", "subjectId"})
public class ClusterProgramSubjectId implements Serializable {

    @Column(name = "cluster_program_id", nullable = false)
    private Integer clusterProgramId;

    @Column(name = "subject_id", nullable = false)
    private Integer subjectId;
}
