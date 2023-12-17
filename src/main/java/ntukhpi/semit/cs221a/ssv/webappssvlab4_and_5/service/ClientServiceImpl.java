package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.service;

import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.Client;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.User;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client saveClient(Client client) {
        return  clientRepository.save(client);
    }

    @Override
    public List<Client> findClientsByEmailOrPhone(Client client) {
        return clientRepository.findByEmailOrPhone(client.getEmail(), client.getPhone());
    }

    @Override
    public Optional<Client> findClientByPhone(String phone) {
        return clientRepository.findClientByPhone(phone);
    }

    @Override
    public Optional<Client> findClientByEmail(String email) {
        return clientRepository.findClientByEmail(email);
    }

    @Override
    public Optional<Client> findClientByUser(User user) {
        return clientRepository.findClientByUser(user);
    }


    @Override
    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }
}
