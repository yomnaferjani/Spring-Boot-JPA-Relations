package tn.esprit.autolocapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaiement;

    private BigDecimal montant;

    private LocalDate datePaiement;

    @Enumerated(EnumType.STRING)
    private tn.esprit.autoloc.domain.ModePaiement modePaiement;

    // ASSOCIATION : côté propriétaire (colonne contrat_id_contrat)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference("contrat-paiements")
    private Contrat contrat;




}