
package tn.esprit.autolocapi.service;

import java.util.List;
import tn.esprit.autolocapi.domain.Employe;

public interface IEmployeService {

    Employe create(Employe employe);

    Employe findById(Long id);

    List<Employe> findAll();

    Employe update(Long id, Employe employe);

    void deleteById(Long id);
}
