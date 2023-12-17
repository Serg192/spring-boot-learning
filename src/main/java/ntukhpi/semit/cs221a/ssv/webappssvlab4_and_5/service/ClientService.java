package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.service;


import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.Client;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.User;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Optional<Client> getClientById(Long id);
    List<Client> getAllClients();
    Client saveClient(Client client);
    List<Client> findClientsByEmailOrPhone(Client client);

    Optional<Client> findClientByPhone(String phone);
    Optional<Client> findClientByEmail(String email);

    Optional<Client> findClientByUser(User user);
    void deleteClientById(Long id);
}
