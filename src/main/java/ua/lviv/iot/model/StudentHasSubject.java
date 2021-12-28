package ua.lviv.iot.model;

import lombok.*;
import ua.lviv.iot.model.composite_key.StudentSubjectId;

import javax.persistence.*;

@Entity
@Table(name = "student_has_subject")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class StudentHasSubject {

    @EmbeddedId
    private StudentSubjectId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("subjectId")
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    private Student student;

}
