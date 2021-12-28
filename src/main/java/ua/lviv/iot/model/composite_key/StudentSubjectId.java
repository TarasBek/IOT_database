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
@EqualsAndHashCode(of = {"subjectId", "studentId"})
public class StudentSubjectId implements Serializable {

    @Column(name = "subject_id", nullable = false)
    private Integer subjectId;

    @Column(name = "student_id", nullable = false)
    private Integer studentId;
}
