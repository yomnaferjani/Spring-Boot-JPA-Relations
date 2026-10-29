
package tn.esprit.autolocapi.service;

import java.util.List;
import tn.esprit.autolocapi.domain.Equipement;

public interface IEquipementService {

    Equipement create(Equipement equipement);

    Equipement findById(Long id);

    List<Equipement> findAll();

    Equipement update(Long id, Equipement equipement);

    void deleteById(Long id);
}
