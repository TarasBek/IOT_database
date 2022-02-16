package ua.lviv.iot.model;

import lombok.*;
import ua.lviv.iot.model.enums.Position;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "rescuer")
@NoArgsConstructor
@Getter
@Setter
public class Rescuer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "is required and must not be blank")
    @Size(max = 50, message = "must be up to 50 chars")
    @Column(name = "surname", nullable = false)
    private String surname;

    @NotBlank(message = "is required and must not be blank")
    @Size(max = 45, message = "must be up to 45 chars")
    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "position", nullable = false)
    private Position position;

    @Column(name = "is_present", nullable = false)
    private boolean isPresent;

    @OneToMany(mappedBy = "rescuer", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<CallHasRescuer> calls;
}
