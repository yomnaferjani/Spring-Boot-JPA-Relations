package tn.esprit.autolocapi.web.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import tn.esprit.autolocapi.domain.*;
import tn.esprit.autolocapi.web.dto.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IMapper {

    // ----- Agence -----
    AgenceDto toDto(Agence agence);

    @Mapping(target = "idAgence", ignore = true)
    Agence toEntity(AgenceDto dto);

    // ----- Employe -----
    @Mapping(source = "agence.idAgence", target = "idAgence")
    @Mapping(source = "agence.nom", target = "nomAgence")
    EmployeDto toDto(Employe employe);

    @Mapping(target = "idEmploye", ignore = true)
    Employe toEntity(EmployeDto dto);

    // ----- Client -----
    ClientDto toDto(Client client);

    @Mapping(target = "idClient", ignore = true)
    Client toEntity(ClientDto dto);

    // ----- Equipement -----
    EquipementDto toDto(Equipement equipement);

    @Mapping(target = "idEquipement", ignore = true)
    Equipement toEntity(EquipementDto dto);

    // ----- Vehicule -----
    @Mapping(source = "agence.idAgence", target = "idAgence")
    @Mapping(source = "agence.nom", target = "nomAgence")
    VehiculeDto toDto(Vehicule vehicule);

    @Mapping(target = "idVehicule", ignore = true)
    @Mapping(target = "equipements", ignore = true)

    Vehicule toEntity(VehiculeDto dto);

    // ----- Maintenance -----
    @Mapping(source = "vehicule.idVehicule", target = "idVehicule")
    @Mapping(source = "vehicule.immatriculation", target = "immatriculationVehicule")
    MaintenanceDto toDto(Maintenance maintenance);

    @Mapping(target = "idMaintenance", ignore = true)
    Maintenance toEntity(MaintenanceDto dto);

    // ----- Reservation -----
    @Mapping(source = "client.idClient", target = "idClient")
    @Mapping(source = "vehicule.idVehicule", target = "idVehicule")
    ReservationDto toDto(Reservation reservation);

    @Mapping(target = "idReservation", ignore = true)
    Reservation toEntity(ReservationDto dto);

    // ----- Contrat (contient ses paiements) -----
    @Mapping(source = "reservation.idReservation", target = "idReservation")
    ContratDto toDto(Contrat contrat);

    @Mapping(target = "idContrat", ignore = true)
    @Mapping(target = "reservation", ignore = true)
    Contrat toEntity(ContratDto dto);

    // ----- Paiement -----
    @Mapping(source = "contrat.idContrat", target = "idContrat")
    PaiementDto toDto(Paiement paiement);

    @Mapping(target = "idPaiement", ignore = true)
    Paiement toEntity(PaiementDto dto);

    // Composition : chaque paiement doit référencer son contrat
    @AfterMapping
    default void lierPaiements(@MappingTarget Contrat contrat) {
        if (contrat.getPaiements() != null) {
            contrat.getPaiements().forEach(p -> p.setContrat(contrat));
        }
    }
}