package tn.esprit.autolocapi.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ClientDto(
        @Schema(accessMode = Schema.AccessMode.READ_ONLY) Long idClient,
        @NotBlank(message = "Le nom est obligatoire") @Size(max = 50) String nom,
        @NotBlank(message = "Le prénom est obligatoire") @Size(max = 50) String prenom,
        @NotBlank(message = "L'email est obligatoire")
        @Email(message = "L'email n'est pas valide") @Size(max = 100) String email,
        @Size(max = 20) String telephone,
        @NotBlank(message = "Le numéro de permis est obligatoire") @Size(max = 30) String numPermis,
        @NotNull(message = "La date d'inscription est obligatoire") LocalDate dateInscription
) {}