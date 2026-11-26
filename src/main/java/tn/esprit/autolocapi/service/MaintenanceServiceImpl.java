package tn.esprit.autolocapi.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autolocapi.domain.Maintenance;
import tn.esprit.autolocapi.domain.Vehicule;
import tn.esprit.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autolocapi.repository.IMaintenanceRepository;
import tn.esprit.autolocapi.repository.IVehiculeRepository;

import static tn.esprit.autolocapi.service.Validations.*;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final IMaintenanceRepository maintenanceRepository;
    private final IVehiculeRepository vehiculeRepository;

    @Override
    public Maintenance create(Maintenance maintenance) {
        if (maintenance.getIdMaintenance() != null) {
            throw new IllegalArgumentException(
                    "Une nouvelle maintenance ne doit pas avoir d'identifiant");
        }
        valider(maintenance);
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public List<Maintenance> findByIdVehicule(Long idVehicule) {

        if (!vehiculeRepository.existsById(idVehicule)) {
            throw new ResourceNotFoundException("Vehicule", idVehicule);
        }

        return maintenanceRepository.findByVehicule_IdVehicule(idVehicule);
    }


    @Override
    public Maintenance findById(Long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance", id));
    }

    @Override
    public List<Maintenance> findAll() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance update(Long id, Maintenance maintenance) {
        Maintenance existante = findById(id);
        valider(maintenance);
        existante.setDateDebut(maintenance.getDateDebut());
        existante.setDateFin(maintenance.getDateFin());
        existante.setDescription(maintenance.getDescription());
        return maintenanceRepository.save(existante);
    }

    @Override
    public void deleteById(Long id) {
        if (!maintenanceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Maintenance", id);
        }
        maintenanceRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Maintenance affecterMaintenanceAVehicule(Long idMaintenance, Long idVehicule) {
        requireNonNull(idMaintenance, "idMaintenance");
        requireNonNull(idVehicule, "idVehicule");

        Maintenance maintenance = findById(idMaintenance);
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", idVehicule));

        // Côté propriétaire : Maintenance porte la clé étrangère id_vehicule
        maintenance.setVehicule(vehicule);
        return maintenanceRepository.save(maintenance);
    }

    private void valider(Maintenance m) {
        requireNonNull(m.getDateDebut(), "dateDebut");
        requireNotBefore(m.getDateDebut(), m.getDateFin());
    }
}