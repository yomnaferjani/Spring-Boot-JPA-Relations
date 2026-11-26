package tn.esprit.autolocapi.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
// Dans TON projet, les enums sont dans tn.esprit.autoloc.domain
import tn.esprit.autoloc.domain.RoleEmploye;

public record EmployeDto(
        @Schema(accessMode = Schema.AccessMode.READ_ONLY) Long idEmploye,
        @NotBlank(message = "Le nom est obligatoire") @Size(max = 50) String nom,
        @NotBlank(message = "Le prénom est obligatoire") @Size(max = 50) String prenom,
        @NotNull(message = "Le rôle est obligatoire") RoleEmploye role,
        @Schema(accessMode = Schema.AccessMode.READ_ONLY) Long idAgence,
        @Schema(accessMode = Schema.AccessMode.READ_ONLY) String nomAgence
) {}