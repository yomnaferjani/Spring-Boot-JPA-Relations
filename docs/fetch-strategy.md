# Stratégie de fetch, cascade et orphanRemoval – AutoLoc

Toutes les associations utilisent `FetchType.LAZY` : on ne charge une
association que lorsque le code en a besoin, ce qui évite de charger
toute la chaîne d'objets liés (par défaut, `@ManyToOne` et `@OneToOne`
sont EAGER).

| Association | Fetch | Cascade | Justification |
|---|---|---|---|
| Contrat → Paiement | LAZY | ALL + orphanRemoval | Un paiement n'existe que rattaché à son contrat (composition) ; supprimer le contrat supprime ses paiements, et retirer un paiement de la liste le supprime en base. |
| Agence → Vehicule | LAZY | Aucune | Un véhicule survit à la suppression de son agence ; il peut être réaffecté. |
| Agence → Employe | LAZY | Aucune | Un employé existe indépendamment d'une agence ; on ne veut pas supprimer des employés en supprimant une agence. |
| Vehicule ↔ Equipement | LAZY | Aucune | Les équipements sont partagés entre plusieurs véhicules ; supprimer un véhicule ne doit pas supprimer un équipement utilisé ailleurs. Utilisation d'un `Set` pour éviter les doublons. |
| Client → Reservation | LAZY | PERSIST | Enregistrer un client avec de nouvelles réservations les enregistre aussi ; pas de REMOVE car l'historique des réservations doit être conservé. |
| Reservation → Vehicule | LAZY | Aucune | Le véhicule a son propre cycle de vie ; une réservation ne doit jamais supprimer ou modifier le véhicule. |
| Reservation ↔ Contrat | LAZY | ALL (côté Reservation) | Le contrat est la conséquence directe de la réservation : il est enregistré, mis à jour et supprimé avec elle. |
| Vehicule → Maintenance | LAZY | PERSIST | Enregistrer un véhicule avec ses maintenances les enregistre aussi ; pas de REMOVE pour conserver l'historique de maintenance. |

## Remarques

- Côté propriétaire (clé étrangère) : `Paiement`, `Vehicule`, `Employe`,
  `Reservation`, `Contrat` (OneToOne), `Maintenance` et `Vehicule`
  (ManyToMany, table `vehicule_equipement`).
- `@Data` n'est pas utilisé, afin d'éviter les `StackOverflowError`
  causés par `toString` / `hashCode` sur des associations bidirectionnelles.
- Sur `Reservation.contrat` (côté inverse du `@OneToOne`), `LAZY` est
  déclaré mais Hibernate peut quand même charger l'objet : c'est une
  limite connue des `@OneToOne` inverses.