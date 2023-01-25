package com.skypro.sharehome.repository;

import com.skypro.sharehome.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client getClientByIdChat(Long idChat);

    Client getClientByName(String name);

    @Query(value = "SELECT * FROM client WHERE count_days >= :count", nativeQuery = true)
    List<Client> findClientsThanDoneTrialPeriod(Integer count);
}
