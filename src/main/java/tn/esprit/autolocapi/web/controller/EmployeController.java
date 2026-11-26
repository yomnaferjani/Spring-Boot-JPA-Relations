package tn.esprit.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autolocapi.service.IEmployeService;
import tn.esprit.autolocapi.web.dto.EmployeDto;
import tn.esprit.autolocapi.web.mapper.IMapper;

import java.util.List;

@RestController
@RequestMapping("/employes")
@RequiredArgsConstructor
@Tag(name = "Employés", description = "Gestion des employés")
public class EmployeController {

    private final IEmployeService employeService;
    private final IMapper mapper;

    @Operation(summary = "Lister les employés")
    @GetMapping
    public List<EmployeDto> findAll() {
        return employeService.findAll().stream().map(mapper::toDto).toList();
    }

    @Operation(summary = "Consulter un employé")
    @GetMapping("/{id}")
    public EmployeDto findById(@PathVariable Long id) {
        return mapper.toDto(employeService.findById(id));
    }

    @Operation(summary = "Créer un employé")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeDto create(@Valid @RequestBody EmployeDto dto) {
        return mapper.toDto(employeService.create(mapper.toEntity(dto)));
    }

    @Operation(summary = "Modifier un employé")
    @PutMapping("/{id}")
    public EmployeDto update(@PathVariable Long id, @Valid @RequestBody EmployeDto dto) {
        return mapper.toDto(employeService.update(id, mapper.toEntity(dto)));
    }

    @Operation(summary = "Supprimer un employé")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        employeService.deleteById(id);
    }
}