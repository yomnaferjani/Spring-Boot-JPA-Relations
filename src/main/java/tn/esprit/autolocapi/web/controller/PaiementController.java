package tn.esprit.autolocapi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autolocapi.service.IPaiementService;
import tn.esprit.autolocapi.web.dto.PaiementDto;
import tn.esprit.autolocapi.web.mapper.IMapper;

import java.util.List;

@RestController
@RequestMapping("/paiements")
@RequiredArgsConstructor
@Tag(name = "Paiements", description = "Consultation des paiements (créés via le contrat)")
public class PaiementController {

    private final IPaiementService paiementService;
    private final IMapper mapper;

    @Operation(summary = "Lister les paiements")
    @GetMapping
    public List<PaiementDto> findAll() {
        return paiementService.findAll().stream().map(mapper::toDto).toList();
    }

    @Operation(summary = "Consulter un paiement")
    @GetMapping("/{id}")
    public PaiementDto findById(@PathVariable Long id) {
        return mapper.toDto(paiementService.findById(id));
    }
}