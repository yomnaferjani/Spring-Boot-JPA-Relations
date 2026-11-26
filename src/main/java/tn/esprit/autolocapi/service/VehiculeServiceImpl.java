package tn.esprit.autolocapi.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autolocapi.domain.Equipement;
import tn.esprit.autolocapi.domain.Vehicule;
import tn.esprit.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autolocapi.repository.IEquipementRepository;
import tn.esprit.autolocapi.repository.IVehiculeRepository;

import static tn.esprit.autolocapi.service.Validations.*;
import tn.esprit.autolocapi.repository.IContratRepository;
import tn.esprit.autolocapi.repository.IMaintenanceRepository;
import tn.esprit.autolocapi.domain.Maintenance;
@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;
    private final IEquipementRepository equipementRepository;
    private final IContratRepository contratRepository;
    private final IMaintenanceRepository maintenanceRepository;
    @Override
    public Vehicule create(Vehicule vehicule) {
        if (vehicule.getIdVehicule() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau véhicule ne doit pas avoir d'identifiant");
        }
        valider(vehicule);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule findById(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", id));
    }

    @Override
    public List<Vehicule> findAll() {
        return vehiculeRepository.findAll();
    }


    @Override
    public List<Vehicule> findByIdContrat(Long idContrat) {

        if (!contratRepository.existsById(idContrat)) {
            throw new ResourceNotFoundException("Contrat", idContrat);
        }

        return vehiculeRepository.findByReservations_Contrat_IdContrat(idContrat);
    }

    @Override
    public List<Vehicule> findByIdMaintenance(Long idMaintenance) {

        if (!maintenanceRepository.existsById(idMaintenance)) {
            throw new ResourceNotFoundException(
                    "Maintenance", idMaintenance
            );
        }

        return vehiculeRepository.findVehiculesByMaintenanceId(idMaintenance);
    }


    @Override
    public Vehicule update(Long id, Vehicule vehicule) {
        Vehicule existant = findById(id);
        valider(vehicule);
        existant.setImmatriculation(vehicule.getImmatriculation());
        existant.setMarque(vehicule.getMarque());
        existant.setModele(vehicule.getModele());
        existant.setCategorie(vehicule.getCategorie());
        existant.setTarifJournalier(vehicule.getTarifJournalier());
        existant.setStatut(vehicule.getStatut());
        return vehiculeRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vehicule", id);
        }
        vehiculeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Vehicule affecterVehiculeAEquipement(Long idVehicule, Long idEquipement) {
        // 1) Les deux paramètres sont obligatoires
        requireNonNull(idVehicule, "idVehicule");
        requireNonNull(idEquipement, "idEquipement");

        // 2) On charge les deux entités : 404 si l'une des deux n'existe pas
        Vehicule vehicule = findById(idVehicule);
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", idEquipement));

        // 3) Côté propriétaire (Vehicule) : add() renvoie false si déjà présent
        if (!vehicule.getEquipements().add(equipement)) {
            throw new IllegalArgumentException(
                    "L'équipement '" + equipement.getLibelle() + "' est déjà affecté à ce véhicule");
        }

        // 4) On enregistre et on retourne le véhicule mis à jour
        return vehiculeRepository.save(vehicule);
    }

    private void valider(Vehicule v) {
        requireText(v.getImmatriculation(), "immatriculation");
        requireText(v.getMarque(), "marque");
        requireText(v.getModele(), "modele");
        requireNonNull(v.getCategorie(), "categorie");
        requireNonNull(v.getStatut(), "statut");
        requireNonNegative(v.getTarifJournalier(), "tarifJournalier");
    }
}