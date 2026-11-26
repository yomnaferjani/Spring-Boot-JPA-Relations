package tn.esprit.autolocapi.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ContratDto(
        @Schema(accessMode = Schema.AccessMode.READ_ONLY) Long idContrat,
        @NotNull(message = "La date de signature est obligatoire") LocalDate dateSignature,
        @NotNull(message = "Le montant total est obligatoire")
        @DecimalMin(value = "0.0", message = "Le montant total ne peut pas être négatif") BigDecimal montantTotal,
        boolean valide,
        @Schema(accessMode = Schema.AccessMode.READ_ONLY) Long idReservation,
        @Valid List<PaiementDto> paiements
) {}