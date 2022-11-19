package com.skypro.sharehome.repository;

import com.skypro.sharehome.entity.Shedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SheduleRepository extends JpaRepository<Shedule, Long> {

    List<Shedule> getShedulesByShareHome(Long shareHome);
}
