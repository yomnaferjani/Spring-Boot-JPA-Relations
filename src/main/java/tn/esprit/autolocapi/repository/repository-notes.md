# Notes – Couche Repository (Atelier 3)

## Choix d'interface

Toutes les interfaces étendent `JpaRepository<Entité, Long>` : on obtient
le CRUD, des `List` (et non des `Iterable`), le tri, la pagination et les
méthodes propres à JPA (`flush`, `saveAndFlush`…).

| Interface | Étend | Justification |
|---|---|---|
| IContratRepository | JpaRepository<Contrat, Long> | CRUD complet, findAll renvoie une List, saveAndFlush disponible. |
| IAgenceRepository | JpaRepository<Agence, Long> | à compléter |
| IEmployeRepository | JpaRepository<Employe, Long> | à compléter |
| IVehiculeRepository | JpaRepository<Vehicule, Long> | à compléter |
| IEquipementRepository | JpaRepository<Equipement, Long> | à compléter |
| IClientRepository | JpaRepository<Client, Long> | à compléter |
| IReservationRepository | JpaRepository<Reservation, Long> | à compléter |
| IPaiementRepository | JpaRepository<Paiement, Long> | Permet de lire les paiements ; création et suppression passent par le Contrat (composition). |
| IMaintenanceRepository | JpaRepository<Maintenance, Long> | à compléter |

## Anomalies SonarQube for IDE

| Anomalie | Règle / explication | Correction apportée |
|---|---|---|
| à compléter | à compléter | à compléter |
| à compléter | à compléter | à compléter |
| à compléter | à compléter | à compléter |