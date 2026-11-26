package tn.esprit.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autolocapi.service.IClientService;
import tn.esprit.autolocapi.web.dto.ClientDto;
import tn.esprit.autolocapi.web.mapper.IMapper;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Tag(name = "Clients", description = "Gestion des clients")
public class ClientController {

    private final IClientService clientService;
    private final IMapper mapper;

    @Operation(summary = "Lister les clients")
    @GetMapping
    public List<ClientDto> findAll() {
        return clientService.findAll().stream().map(mapper::toDto).toList();
    }

    @Operation(summary = "Consulter un client")
    @GetMapping("/{id}")
    public ClientDto findById(@PathVariable Long id) {
        return mapper.toDto(clientService.findById(id));
    }

    @Operation(summary = "Créer un client")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto create(@Valid @RequestBody ClientDto dto) {
        return mapper.toDto(clientService.create(mapper.toEntity(dto)));
    }

    @Operation(summary = "Modifier un client")
    @PutMapping("/{id}")
    public ClientDto update(@PathVariable Long id, @Valid @RequestBody ClientDto dto) {
        return mapper.toDto(clientService.update(id, mapper.toEntity(dto)));
    }

    @Operation(summary = "Supprimer un client")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
    }
}