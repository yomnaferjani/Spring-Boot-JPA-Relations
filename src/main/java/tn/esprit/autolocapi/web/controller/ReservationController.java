package tn.esprit.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autolocapi.service.IReservationService;
import tn.esprit.autolocapi.web.dto.ReservationDto;
import tn.esprit.autolocapi.web.mapper.IMapper;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
@Tag(name = "Réservations", description = "Gestion des réservations")
public class ReservationController {

    private final IReservationService reservationService;
    private final IMapper mapper;

    @Operation(summary = "Lister les réservations")
    @GetMapping
    public List<ReservationDto> findAll() {
        return reservationService.findAll().stream().map(mapper::toDto).toList();
    }

    @Operation(summary = "Consulter une réservation")
    @GetMapping("/{id}")
    public ReservationDto findById(@PathVariable Long id) {
        return mapper.toDto(reservationService.findById(id));
    }

    @Operation(summary = "Créer une réservation")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationDto create(@Valid @RequestBody ReservationDto dto) {
        return mapper.toDto(reservationService.create(mapper.toEntity(dto)));
    }

    @Operation(summary = "Modifier une réservation")
    @PutMapping("/{id}")
    public ReservationDto update(@PathVariable Long id, @Valid @RequestBody ReservationDto dto) {
        return mapper.toDto(reservationService.update(id, mapper.toEntity(dto)));
    }

    @Operation(summary = "Supprimer une réservation")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        reservationService.deleteById(id);
    }
}