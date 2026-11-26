package tn.esprit.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autolocapi.service.IEquipementService;
import tn.esprit.autolocapi.web.dto.EquipementDto;
import tn.esprit.autolocapi.web.mapper.IMapper;

import java.util.List;

@RestController
@RequestMapping("/equipements")
@RequiredArgsConstructor
@Tag(name = "Equipements", description = "Gestion des équipements")
public class EquipementController {

    private final IEquipementService equipementService;
    private final IMapper mapper;

    @Operation(summary = "Lister les équipements")
    @GetMapping
    public List<EquipementDto> findAll() {
        return equipementService.findAll().stream().map(mapper::toDto).toList();
    }

    @Operation(summary = "Consulter un équipement")
    @GetMapping("/{id}")
    public EquipementDto findById(@PathVariable Long id) {
        return mapper.toDto(equipementService.findById(id));
    }

    @Operation(summary = "Créer un équipement")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquipementDto create(@Valid @RequestBody EquipementDto dto) {
        return mapper.toDto(equipementService.create(mapper.toEntity(dto)));
    }

    @Operation(summary = "Modifier un équipement")
    @PutMapping("/{id}")
    public EquipementDto update(@PathVariable Long id, @Valid @RequestBody EquipementDto dto) {
        return mapper.toDto(equipementService.update(id, mapper.toEntity(dto)));
    }

    @Operation(summary = "Supprimer un équipement")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        equipementService.deleteById(id);
    }
}