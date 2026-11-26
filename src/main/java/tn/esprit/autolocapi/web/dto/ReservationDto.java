package tn.esprit.autolocapi.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import tn.esprit.autoloc.domain.StatutReservation;

import java.time.LocalDate;

public record ReservationDto(
        @Schema(accessMode = Schema.AccessMode.READ_ONLY) Long idReservation,
        @NotNull(message = "La date de début est obligatoire") LocalDate dateDebut,
        @NotNull(message = "La date de fin est obligatoire") LocalDate dateFin,
        @NotNull(message = "Le statut est obligatoire") StatutReservation statut,
        @Schema(accessMode = Schema.AccessMode.READ_ONLY) Long idClient,
        @Schema(accessMode = Schema.AccessMode.READ_ONLY) Long idVehicule
) {}