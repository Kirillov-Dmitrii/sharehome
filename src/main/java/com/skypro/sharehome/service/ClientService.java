package com.skypro.sharehome.service;

import com.skypro.sharehome.entity.Client;
import com.skypro.sharehome.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client addClient(Client client){
        return clientRepository.save(client);
    }

    public Client findClient(Long id){
        return clientRepository.findById(id).orElse(null);
    }

    public Client updateClient (Client  client){
        return clientRepository.save(client);
    }
}
