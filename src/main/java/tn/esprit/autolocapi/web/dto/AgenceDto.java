package tn.esprit.autolocapi.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AgenceDto(
        @Schema(accessMode = Schema.AccessMode.READ_ONLY) Long idAgence,
        @NotBlank(message = "Le nom est obligatoire") @Size(max = 100) String nom,
        @NotBlank(message = "La ville est obligatoire") @Size(max = 50) String ville,
        @NotBlank(message = "L'adresse est obligatoire") @Size(max = 150) String adresse,
        @Size(max = 20) String telephone
) {}