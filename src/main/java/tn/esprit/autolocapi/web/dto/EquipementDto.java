package tn.esprit.autolocapi.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EquipementDto(
        @Schema(accessMode = Schema.AccessMode.READ_ONLY) Long idEquipement,
        @NotBlank(message = "Le libellé est obligatoire") @Size(max = 100) String libelle
) {}