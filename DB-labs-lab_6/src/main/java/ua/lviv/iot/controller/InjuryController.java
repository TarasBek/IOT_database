package ua.lviv.iot.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dto.injury.InjuryDto;
import ua.lviv.iot.mappers.InjuryMapper;
import ua.lviv.iot.service.InjuryService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static ua.lviv.iot.mappers.InjuryMapper.mapInjuryToInjuryDto;

@RestController
@AllArgsConstructor
@RequestMapping("/api/injury")
public class InjuryController {

    private final InjuryService injuryService;

    @PostMapping
    InjuryDto createInjury(final @Valid @RequestBody InjuryDto injuryDto) {
        return mapInjuryToInjuryDto(injuryService.createInjury(injuryDto));
    }

    @GetMapping
    List<InjuryDto> getAllInjuries() {
        return injuryService.getAllInjuries().stream()
                .map(InjuryMapper::mapInjuryToInjuryDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    InjuryDto getInjuryById(final @PathVariable("id") Integer id) {
        return mapInjuryToInjuryDto(injuryService.getInjuryById(id));
    }

    @PutMapping
    InjuryDto updateInjury(final @Valid @RequestBody InjuryDto injuryDto) {
        return mapInjuryToInjuryDto(injuryService.updateInjury(injuryDto));
    }

    @DeleteMapping("/{id}")
    InjuryDto deleteInjuryById(final @PathVariable("id") Integer id) {
        return mapInjuryToInjuryDto(injuryService.deleteInjuryById(id));
    }
}
