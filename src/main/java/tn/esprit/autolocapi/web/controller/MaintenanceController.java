package tn.esprit.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autolocapi.service.IMaintenanceService;
import tn.esprit.autolocapi.web.dto.MaintenanceDto;
import tn.esprit.autolocapi.web.mapper.IMapper;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
@Tag(name = "Maintenances", description = "Gestion des maintenances")
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;
    private final IMapper mapper;

    @Operation(summary = "Lister les maintenances")
    @GetMapping
    public List<MaintenanceDto> findAll() {
        return maintenanceService.findAll().stream().map(mapper::toDto).toList();
    }

    @Operation(summary = "Consulter une maintenance")
    @GetMapping("/{id}")
    public MaintenanceDto findById(@PathVariable Long id) {
        return mapper.toDto(maintenanceService.findById(id));
    }

    @Operation(summary = "Créer une maintenance")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaintenanceDto create(@Valid @RequestBody MaintenanceDto dto) {
        return mapper.toDto(maintenanceService.create(mapper.toEntity(dto)));
    }

    @Operation(summary = "Modifier une maintenance")
    @PutMapping("/{id}")
    public MaintenanceDto update(@PathVariable Long id, @Valid @RequestBody MaintenanceDto dto) {
        return mapper.toDto(maintenanceService.update(id, mapper.toEntity(dto)));
    }

    @Operation(summary = "Affecter une maintenance à un véhicule")
    @PutMapping("/{idMaintenance}/vehicule/{idVehicule}")
    public MaintenanceDto affecterMaintenanceAVehicule(@PathVariable @Positive Long idMaintenance,
                                                       @PathVariable @Positive Long idVehicule) {
        return mapper.toDto(maintenanceService.affecterMaintenanceAVehicule(idMaintenance, idVehicule));
    }

    @Operation(summary = "Lister les maintenances par ID véhicule")
    @GetMapping("/vehicule/{idVehicule}")
    public List<MaintenanceDto> findByIdVehicule(
            @PathVariable @Positive Long idVehicule) {

        return maintenanceService.findByIdVehicule(idVehicule)
                .stream()
                .map(mapper::toDto)
                .toList();
    }


    @Operation(summary = "Supprimer une maintenance")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        maintenanceService.deleteById(id);
    }
}