package tn.esprit.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autolocapi.service.IAgenceService;
import tn.esprit.autolocapi.web.dto.AgenceDto;
import tn.esprit.autolocapi.web.mapper.IMapper;

import java.util.List;

@RestController
@RequestMapping("/agences")
@RequiredArgsConstructor
@Tag(name = "Agences", description = "Gestion des agences")
public class AgenceController {

    private final IAgenceService agenceService;
    private final IMapper mapper;

    @Operation(summary = "Lister les agences")
    @GetMapping
    public List<AgenceDto> findAll() {
        return agenceService.findAll().stream().map(mapper::toDto).toList();
    }

    @Operation(summary = "Consulter une agence")
    @GetMapping("/{id}")
    public AgenceDto findById(@PathVariable Long id) {
        return mapper.toDto(agenceService.findById(id));
    }

    @Operation(summary = "Créer une agence")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgenceDto create(@Valid @RequestBody AgenceDto dto) {
        return mapper.toDto(agenceService.create(mapper.toEntity(dto)));
    }

    @Operation(summary = "Modifier une agence")
    @PutMapping("/{id}")
    public AgenceDto update(@PathVariable Long id, @Valid @RequestBody AgenceDto dto) {
        return mapper.toDto(agenceService.update(id, mapper.toEntity(dto)));
    }

    @Operation(summary = "Supprimer une agence")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        agenceService.deleteById(id);
    }
}