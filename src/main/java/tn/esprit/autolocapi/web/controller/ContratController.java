package tn.esprit.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autolocapi.service.IContratService;
import tn.esprit.autolocapi.web.dto.ContratDto;
import tn.esprit.autolocapi.web.mapper.IMapper;
import jakarta.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/contrats")
@RequiredArgsConstructor
@Tag(name = "Contrats", description = "Gestion des contrats (avec leurs paiements)")
public class ContratController {

    private final IContratService contratService;
    private final IMapper mapper;

    @Operation(summary = "Lister les contrats")
    @GetMapping
    public List<ContratDto> findAll() {
        return contratService.findAll().stream().map(mapper::toDto).toList();
    }

    @Operation(summary = "Consulter un contrat")
    @GetMapping("/{id}")
    public ContratDto findById(@PathVariable Long id) {
        return mapper.toDto(contratService.findById(id));
    }

    @Operation(summary = "Créer un contrat")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContratDto create(@Valid @RequestBody ContratDto dto) {
        return mapper.toDto(contratService.create(mapper.toEntity(dto)));
    }

    @Operation(summary = "Modifier un contrat")
    @PutMapping("/{id}")
    public ContratDto update(@PathVariable Long id, @Valid @RequestBody ContratDto dto) {
        return mapper.toDto(contratService.update(id, mapper.toEntity(dto)));
    }

    @Operation(summary = "Lister les contrats par ID véhicule")
    @GetMapping("/vehicule/{idVehicule}")
    public List<ContratDto> findByIdVehicule(
            @PathVariable @Positive Long idVehicule) {

        return contratService.findByIdVehicule(idVehicule)
                .stream()
                .map(mapper::toDto)
                .toList();
    }


    @Operation(summary = "Supprimer un contrat")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        contratService.deleteById(id);
    }
}