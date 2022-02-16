package ua.lviv.iot.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "`call`")
@NoArgsConstructor
@Getter
@Setter
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255, message = "must be up to 255 chars")
    @NotBlank(message = "is required and must not be blank")
    @Column(name = "short_description", nullable = false)
    private String shortDescription;

    @Size(max = 3000, message = "must be up to 3000 chars")
    @Column(name = "detailed_description")
    private String detailedDescription;

    @Column(name = "call_time", nullable = false)
    private LocalDateTime callTime;

    @ManyToOne
    @JoinColumn(name = "reporter_phone_number", nullable = false)
    private Reporter reporter;

    @ManyToOne
    @JoinColumn(name = "call_address_id", nullable = false)
    private CallAddress callAddress;

    @OneToMany(mappedBy = "call", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<CallHasRescuer> rescuers;

    @OneToMany(mappedBy = "call", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<CallHasRescueVehicle> rescueVehicles;

    public Call(Integer id, String shortDescription, String detailedDescription, LocalDateTime callTime,
                Reporter reporter, CallAddress callAddress) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.detailedDescription = detailedDescription;
        this.callTime = callTime;
        this.reporter = reporter;
        this.callAddress = callAddress;
    }

    public Call(String shortDescription, String detailedDescription, LocalDateTime callTime, Reporter reporter,
                CallAddress callAddress) {
        this.shortDescription = shortDescription;
        this.detailedDescription = detailedDescription;
        this.callTime = callTime;
        this.reporter = reporter;
        this.callAddress = callAddress;
    }
}
