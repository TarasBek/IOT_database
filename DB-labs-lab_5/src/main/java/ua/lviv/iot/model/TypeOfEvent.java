package ua.lviv.iot.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "type_of_event")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class TypeOfEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type_of_event_description", nullable = false)
    private String typeOfEventDescription;

    @OneToOne(mappedBy = "typeOfEvent", fetch = FetchType.EAGER)
    private Event event;

}