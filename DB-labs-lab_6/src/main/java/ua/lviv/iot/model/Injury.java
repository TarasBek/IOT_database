package ua.lviv.iot.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "injury")
@NoArgsConstructor
@Getter
@Setter
public class Injury {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "diagnosis", nullable = false)
    private String diagnosis;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    public Injury(String description, String diagnosis, Hospital hospital) {
        this.description = description;
        this.diagnosis = diagnosis;
        this.hospital = hospital;
    }

    public Injury(Integer id, String description, String diagnosis, Hospital hospital) {
        this.id = id;
        this.description = description;
        this.diagnosis = diagnosis;
        this.hospital = hospital;
    }
}
