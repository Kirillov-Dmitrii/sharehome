package com.skypro.sharehome.service;

import com.skypro.sharehome.entity.Client;
import com.skypro.sharehome.entity.ShareHome;
import com.skypro.sharehome.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public String addPhoneOfClient(ShareHome shareHome, Long id, String name, String phone) {
        Client client = clientRepository.getClientByIdChat(id);
        if (client.equals(null)) {
            addClient(new Client(name, phone, id, shareHome));
        }
        client.setPhone(phone);
        return "Номер записан";
    }

    public Client changeTrialPeriod(Integer countChangingDay, Boolean addOrDel, String nameClient){
        Client client = clientRepository.getClientByName(nameClient);
        if (client == null) {
            return null;
        }
        if (addOrDel){
            client.setCountDays(client.getCountDays() + countChangingDay);
        } else if (!addOrDel) {
            client.setCountDays(client.getCountDays() - countChangingDay);
        }
        return client;
    }

    public List<Client> getClientsDoneTrialPeriod(Integer countNeedDone){
        return clientRepository.findClientsThanDoneTrialPeriod(countNeedDone);
    }
}
