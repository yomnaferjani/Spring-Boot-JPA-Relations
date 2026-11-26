package tn.esprit.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autolocapi.service.IVehiculeService;
import tn.esprit.autolocapi.web.dto.VehiculeDto;
import tn.esprit.autolocapi.web.mapper.IMapper;

import java.util.List;

@RestController
@RequestMapping("/vehicules")
@RequiredArgsConstructor
@Tag(name = "Véhicules", description = "Gestion des véhicules")
public class VehiculeController {

    private final IVehiculeService vehiculeService;
    private final IMapper mapper;

    @Operation(summary = "Lister les véhicules")
    @GetMapping
    public List<VehiculeDto> findAll() {
        return vehiculeService.findAll().stream().map(mapper::toDto).toList();
    }

    @Operation(summary = "Consulter un véhicule")
    @GetMapping("/{id}")
    public VehiculeDto findById(@PathVariable Long id) {
        return mapper.toDto(vehiculeService.findById(id));
    }

    @Operation(summary = "Créer un véhicule")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehiculeDto create(@Valid @RequestBody VehiculeDto dto) {
        return mapper.toDto(vehiculeService.create(mapper.toEntity(dto)));
    }

    @Operation(summary = "Modifier un véhicule")
    @PutMapping("/{id}")
    public VehiculeDto update(@PathVariable Long id, @Valid @RequestBody VehiculeDto dto) {
        return mapper.toDto(vehiculeService.update(id, mapper.toEntity(dto)));
    }

    @Operation(summary = "Affecter un équipement à un véhicule")
    @PutMapping("/{idVehicule}/equipements/{idEquipement}")
    public VehiculeDto affecterVehiculeAEquipement(@PathVariable @Positive Long idVehicule,
                                                   @PathVariable @Positive Long idEquipement) {
        return mapper.toDto(vehiculeService.affecterVehiculeAEquipement(idVehicule, idEquipement));
    }

    @Operation(summary = "Lister les véhicules par ID contrat")
    @GetMapping("/contrat/{idContrat}")
    public List<VehiculeDto> findByIdContrat(
            @PathVariable @Positive Long idContrat) {

        return vehiculeService.findByIdContrat(idContrat)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Operation(summary = "Lister les véhicules par ID maintenance")
    @GetMapping("/maintenance/{idMaintenance}")
    public List<VehiculeDto> findByIdMaintenance(
            @PathVariable @Positive Long idMaintenance) {

        return vehiculeService.findByIdMaintenance(idMaintenance)
                .stream()
                .map(mapper::toDto)
                .toList();
    }


    @Operation(summary = "Supprimer un véhicule")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        vehiculeService.deleteById(id);
    }
}