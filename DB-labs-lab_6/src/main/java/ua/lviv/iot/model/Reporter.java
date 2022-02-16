package ua.lviv.iot.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "reporter")
@NoArgsConstructor
@Getter
@Setter
public class Reporter {

    @Id
    @Column(name = "phone_number", nullable = false)
    @NotBlank(message = "is required and must not be blank")
    @Size(max = 12, message = "must be up to 12 chars")
    @Pattern(regexp = "\\d{12}", message = "enter valid phone number")
    private String phoneNumber;

    @NotBlank(message = "is required and must not be blank")
    @Size(max = 50, message = "must be up to 50 chars")
    @Column(name = "surname", nullable = false)
    private String surname;

    @Size(max = 40, message = "must be up to 40 chars")
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "reporter", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Call> calls;
}
