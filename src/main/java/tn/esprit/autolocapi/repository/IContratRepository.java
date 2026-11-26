
package tn.esprit.autolocapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autolocapi.domain.Contrat;

import java.util.List;

public interface IContratRepository extends JpaRepository<Contrat, Long> {

    // Afficher les contrats par ID véhicule
    List<Contrat> findByReservation_Vehicule_IdVehicule(Long idVehicule);
}
