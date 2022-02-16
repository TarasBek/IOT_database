package ua.lviv.iot.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "artist_or_group")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class ArtistOrGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "artist_description", nullable = false)
    private String artistDescription;

    @OneToOne(mappedBy = "artistOrGroup", fetch = FetchType.EAGER)
    private Event event;


}
