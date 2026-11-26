
package tn.esprit.autolocapi.service;

import java.util.List;
import tn.esprit.autolocapi.domain.Maintenance;

public interface IMaintenanceService {

    Maintenance create(Maintenance maintenance);

    Maintenance findById(Long id);

    List<Maintenance> findAll();

    Maintenance update(Long id, Maintenance maintenance);

    void deleteById(Long id);

    /** Affecte une maintenance existante à un véhicule existant. */
    Maintenance affecterMaintenanceAVehicule(Long idMaintenance, Long idVehicule);

    // Afficher les maintenances par ID véhicule
    List<Maintenance> findByIdVehicule(Long idVehicule);
}
