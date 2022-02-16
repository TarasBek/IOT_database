package ua.lviv.iot.dto.calladdress;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CallAddressDto {

    private Integer id;

    private String region;

    private String cityOrVillage;

    private String street;

    private String building;

    private String flat;

    private String place;

    private Set<Integer> calls;
}
