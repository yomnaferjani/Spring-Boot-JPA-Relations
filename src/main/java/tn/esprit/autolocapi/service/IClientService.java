
package tn.esprit.autolocapi.service;

import java.util.List;
import tn.esprit.autolocapi.domain.Client;

public interface IClientService {

    Client create(Client client);

    Client findById(Long id);

    List<Client> findAll();

    Client update(Long id, Client client);

    void deleteById(Long id);
}
