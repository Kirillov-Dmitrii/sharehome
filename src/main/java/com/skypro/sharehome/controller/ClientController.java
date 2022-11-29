package com.skypro.sharehome.controller;

import com.skypro.sharehome.entity.Client;
import com.skypro.sharehome.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public Client addClient(@RequestBody Client client){
        return clientService.addClient(client);
    }

    @GetMapping("{id}")
    public ResponseEntity<Client> getClient (@PathVariable Long id){
        Client client= clientService.findClient(id);
        if (client== null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }

    @PutMapping
    public ResponseEntity<Client> updateClient(@RequestBody Client client){
        Client client1= clientService.updateClient(client);
        if(client1== null){
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(client1);
    }
}
