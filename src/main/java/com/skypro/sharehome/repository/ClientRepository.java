package com.skypro.sharehome.repository;

import com.skypro.sharehome.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client getClientByIdChat(Long idChat);
}
