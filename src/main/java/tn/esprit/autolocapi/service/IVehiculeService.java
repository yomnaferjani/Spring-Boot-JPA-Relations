
package tn.esprit.autolocapi.service;

import java.util.List;
import tn.esprit.autolocapi.domain.Vehicule;

public interface IVehiculeService {

    Vehicule create(Vehicule vehicule);

    Vehicule findById(Long id);

    List<Vehicule> findAll();

    Vehicule update(Long id, Vehicule vehicule);

    void deleteById(Long id);

    /** Affecte un équipement existant à un véhicule existant. */
    Vehicule affecterVehiculeAEquipement(Long idVehicule, Long idEquipement);

    // Afficher les véhicules par ID contrat
    List<Vehicule> findByIdContrat(Long idContrat);

    // Afficher les véhicules par ID maintenance
    List<Vehicule> findByIdMaintenance(Long idMaintenance);
}
