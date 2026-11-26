
package tn.esprit.autolocapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.autolocapi.domain.Vehicule;

import java.util.List;

public interface IVehiculeRepository extends JpaRepository<Vehicule, Long> {

    // Afficher les véhicules par ID contrat
    List<Vehicule> findByReservations_Contrat_IdContrat(Long idContrat);
    @Query(value = """
    SELECT v.*
    FROM vehicule v
    INNER JOIN maintenance m
        ON m.id_vehicule = v.id_vehicule
    WHERE m.id_maintenance = :idMaintenance
    """, nativeQuery = true)
    List<Vehicule> findVehiculesByMaintenanceId(
            @Param("idMaintenance") Long idMaintenance
    );
}
