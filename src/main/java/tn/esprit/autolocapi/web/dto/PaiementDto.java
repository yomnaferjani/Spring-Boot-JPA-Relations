package tn.esprit.autolocapi.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import tn.esprit.autoloc.domain.ModePaiement;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaiementDto(
        @Schema(accessMode = Schema.AccessMode.READ_ONLY) Long idPaiement,
        @NotNull(message = "Le montant est obligatoire")
        @DecimalMin(value = "0.0", inclusive = false, message = "Le montant doit être strictement positif") BigDecimal montant,
        @NotNull(message = "La date de paiement est obligatoire") LocalDate datePaiement,
        @NotNull(message = "Le mode de paiement est obligatoire") ModePaiement modePaiement,
        @Schema(accessMode = Schema.AccessMode.READ_ONLY) Long idContrat
) {}