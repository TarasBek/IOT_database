package ua.lviv.iot.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "event")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "type_of_event_id", nullable = false)
    private TypeOfEvent typeOfEvent;

    @OneToOne
    @JoinColumn(name = "artist_or_group_id", nullable = false)
    private ArtistOrGroup artistOrGroup;

    @Column(name = "data", nullable = false)
    private LocalDateTime data;

    @Column(name = "duration", nullable = false)
    private String duration;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "max_seats_available", nullable = false)
    private Integer maxSeatsAvailable;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    private Set<Ticket> ticket;
}
